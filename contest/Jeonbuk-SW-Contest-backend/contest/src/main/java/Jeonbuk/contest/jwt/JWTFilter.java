package Jeonbuk.contest.jwt;

import Jeonbuk.contest.entity.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.info("JWT Token is null");
            //다음 필터로 넘겨주기
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1]; //뒷부분만 추출

        if (jwtUtils.isExpired(token)) {
            log.info("JWT Token Expired");
            filterChain.doFilter(request, response);
            return;
        }

        String memberId = jwtUtils.getMemberId(token);
        Member member = new Member();
        member.setId(memberId);
        member.setPassword("tempPassword"); //임시 비밀번호

        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);

    }
}
