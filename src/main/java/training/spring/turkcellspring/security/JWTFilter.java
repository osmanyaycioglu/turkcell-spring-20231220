package training.spring.turkcellspring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService          jwtService;
    private final MyUserDetailService myUserDetailService;
    private List<String> allowedList = List.of("/auth","/actuator","/h2-console");


    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        String servletPathLoc   = request.getServletPath();
        if (servletPathLoc != null && allowedList.stream().anyMatch(s -> servletPathLoc.startsWith(s))){
            filterChain.doFilter(request,
                                 response);
            return;
        }
        String authorizationLoc = request.getHeader("Authorization");
        if (authorizationLoc == null || !authorizationLoc.startsWith("Bearer")) {
            errorResponse(response,
                          "Token missing");
            return;
        }
        String      token       = authorizationLoc.substring(7);
        Jws<Claims> validateLoc = jwtService.validate(token);
        if (validateLoc == null) {
            errorResponse(response,
                          "Token not valid");
            return;
        }

        Claims      payloadLoc     = validateLoc.getPayload();
        String      username       = payloadLoc.getSubject();
        UserDetails userDetailsLoc = myUserDetailService.loadUserByUsername(username);
        if (userDetailsLoc == null) {
            errorResponse(response,
                          "Token not valid");
            return;
        }
        Authentication authenticationLoc = new UsernamePasswordAuthenticationToken(userDetailsLoc.getUsername(),
                                                                                   userDetailsLoc.getPassword(),
                                                                                   userDetailsLoc.getAuthorities());
        SecurityContextHolder.getContext()
                             .setAuthentication(authenticationLoc);
        filterChain.doFilter(request,
                             response);
    }

    private static void errorResponse(final HttpServletResponse response,
                                      String message) throws IOException {
        response.setStatus(401);
        response.addHeader("Content-Type",
                           "application/json");
        response.getWriter()
                .println(message);
    }
}
