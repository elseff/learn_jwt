package ru.elseff.learn_jwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.elseff.learn_jwt.exception.ApiErrorResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws IOException, ServletException {
        if (!request.getRequestURI().startsWith("/api/v1/auth")) {
            String token = jwtProvider.getTokenFromRequest(request);
            try {
                if (token != null && jwtProvider.validateToken(token)) {
                    String username = jwtProvider.getUsernameFromToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (JwtException e) {
                SecurityContextHolder.clearContext();
                ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
                String errorAsString = objectMapper.writeValueAsString(errorResponse);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getOutputStream().write(errorAsString.getBytes(StandardCharsets.UTF_8));
            }
        }
        chain.doFilter(request, response);
    }
}

