package com.example.potatotilnewsfeed.global.filter;

import com.example.potatotilnewsfeed.global.dto.Exception.ExceptionDto;
import com.example.potatotilnewsfeed.global.jwt.JwtUtil;
import com.example.potatotilnewsfeed.global.security.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.security.auth.login.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
        FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = jwtUtil.getJwtFromHeader(req);


        String tokenValue = jwtUtil.getTokenWithoutBearer(bearerToken);

        if (StringUtils.hasText(tokenValue)) {

            if (!jwtUtil.validateToken(tokenValue)) {
                log.error("Token Error");
                return;
            }

            try {
                userDetailsService.validateToken(bearerToken);
            } catch (LoginException e) {
                log.error(e.getMessage());

                res.setStatus(400);
                res.setContentType("application/json");
                res.setCharacterEncoding("utf-8");

                ExceptionDto exceptionDto = ExceptionDto.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .state(HttpStatus.BAD_REQUEST)
                    .message("이미 로그아웃 처리된 토큰입니다. 다시 로그인하세요.")
                    .build();

                String exception = objectMapper.writeValueAsString(exceptionDto);
                res.getWriter().write(exception);
                return;
            }


            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

            try {
                setAuthentication(info.getSubject(), tokenValue);
            } catch (Exception e) {
                log.error(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(req, res);
    }

    // 인증 처리
    public void setAuthentication(String username, String tokenValue) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username, tokenValue);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username, String tokenValue) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    }
}
