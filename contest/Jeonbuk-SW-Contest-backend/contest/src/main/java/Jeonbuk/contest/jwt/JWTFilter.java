package Jeonbuk.contest.jwt;

import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public JWTFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.info("[doFilterInternal] JWT Token is null, requestURL: {}", request.getRequestURL());
            //다음 필터로 넘겨주기
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1]; //뒷부분만 추출

        if (jwtUtils.isExpired(token)) {
            log.info("[doFilterInternal] JWT Token Expired, requestURL: {}", request.getRequestURL());
            filterChain.doFilter(request, response);
            return;
        }

        String memberId = jwtUtils.getMemberId(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(memberId);
        log.info("[doFilterInternal] 토큰 인증 정보 조회 완료, UserDetails userId: {}, userName: {}", memberId, userDetails.getUsername());

        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);

    }
}
