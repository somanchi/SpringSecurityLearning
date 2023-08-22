package sh.radical.school.configurations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import sh.radical.school.exceptions.UnAuthorizedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class BasicAuthenticationFilter extends OncePerRequestFilter {

    @Value("${authentication.user}")
    private String userName;

    @Value("${authentication.password}")
    private String password;

    private final List<String> disabledAuth = List.of(
            "PUT:/v1/employees"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (! authorizationHeader.startsWith("Basic")) {
            handleUnAuthorizedException(response, "Invalid Authorization header");
            return;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(authorizationHeader.split(" ")[1]);
        String decodedString = new String(decodedBytes);
        var userData = decodedString.split(":");
        if ( ! userName.equals(userData[0]) || ! password.equals(userData[1] ) ) {
            handleUnAuthorizedException(response, "Invalid UserName or Password");
            return;
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userData[0], userData[1]);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authRequest);
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return disabledAuth.stream().anyMatch(p -> {
            var value = p.split(":");
            return pathMatcher.match(value[1], request.getServletPath()) && value[0].equals(request.getMethod());
        } );
    }

    private void handleUnAuthorizedException(HttpServletResponse response,String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(message);
    }
}

