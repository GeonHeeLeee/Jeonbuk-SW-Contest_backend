package Jeonbuk.contest.jwt;

import Jeonbuk.contest.domain.MemberAuthDTO;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final Gson gson = new Gson();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        MemberAuthDTO memberAuthDTO;
        try {
            memberAuthDTO = gson.fromJson(request.getReader(), MemberAuthDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String memberId = memberAuthDTO.getId();
        String password = memberAuthDTO.getPassword();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(memberId, password, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공 시 실행
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String memberId = customUserDetails.getUsername();
        String token = jwtUtils.createJwt(memberId, 30 * 60 * 60 * 10L);
        log.info("로그인 성공 - url: {}, memberId: {}", request.getRequestURL(), memberId);
        response.addHeader("Authorization", "Bearer " + token);

    }

    //로그인 실패 시 실행
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        log.info("로그인 실패 - url: {}", request.getRequestURL());
        response.setStatus(401);
    }
}
