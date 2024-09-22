package com.myproject.notification_service.config.security;

import com.myproject.notification_service.config.auth_service.AuthResponse;
import com.myproject.notification_service.config.auth_service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nguyenle
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = extractToken(request);
        AuthResponse authResponse = authService.validate(authHeader);
        if (authResponse.isAuthenticated()) {
            UserSimpleDetails userSimpleDetails = authResponse.getUserSimpleDetails();
            List<SimpleGrantedAuthority> authorities = extractAuthorities(authResponse);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userSimpleDetails, null, authorities
            );
            token.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

    private List<SimpleGrantedAuthority> extractAuthorities(AuthResponse authResponse) {
        if (authResponse.getRoles().isEmpty()) {
            return List.of(new SimpleGrantedAuthority("USER"));
        }
        return authResponse.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.toUpperCase()))
                .collect(Collectors.toList());
    }
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || header.isEmpty()) {
            return "";
        }
        return header;
    }
}
