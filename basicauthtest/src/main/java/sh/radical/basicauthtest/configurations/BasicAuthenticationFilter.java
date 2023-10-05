package sh.radical.basicauthtest.configurations;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Base64;
//import java.util.List;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//@Slf4j
//public class BasicAuthenticationFilter extends OncePerRequestFilter {
//
//    @Value(value = "${authentication.user}")
//    private String userName;
//
//    @Value(value = "${authentication.password}")
//    private String password;
//
//    @Autowired
//    public ObjectMapper objectMapper;
//
//    List<String> disabledAuth = List.of(
//            "GET:/v1/cars",
//            "DELETE:/v1/cars/{\\d+}",
//            "PUT:/v1/cars/{\\d+}",
//            "GET:/v1/cars/{\\d+}"
//    );
//
//    List<String> AUTH_WHITELIST = List.of(
//            "/v3/api-docs",
//            "/actuator",
//            "/swagger-ui",
//            "/swagger-ui.html",
//            "/health"
//    );
//
//    public void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        String authorizationHeader = request.getHeader(
//                HttpHeaders.AUTHORIZATION
//        );
//        if (
//                authorizationHeader == null ||
//                        authorizationHeader.isBlank() ||
//                        !authorizationHeader.startsWith("Basic ")
//        ) {
//            log.warn(
//                    "Authorization Header is invalid, headerValue: {}",
//                    authorizationHeader
//            );
//            handleUnAuthorizedException(
//                    response,
//                    "Invalid Authorization header"
//            );
//            return;
//        }
//        try {
//            byte[] decodedBytes = Base64
//                    .getDecoder()
//                    .decode(authorizationHeader.split(" ")[1]);
//
//            String decodedString = new String(decodedBytes);
//            var userData = decodedString.split(":");
//            if (
//                    !userName.equals(userData[0]) || !password.equals(userData[1])
//            ) {
//                handleUnAuthorizedException(
//                        response,
//                        "Invalid UserName or Password"
//                );
//                return;
//            }
//
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
//                    userData[0],
//                    userData[1]
//            );
//            SecurityContext securityContext = SecurityContextHolder.getContext();
//            securityContext.setAuthentication(authRequest);
//            filterChain.doFilter(request, response);
//        } catch (Exception e) {
//            log.warn(
//                    "Failed to decode Authorization, headerValue: {}  exception: {}",
//                    authorizationHeader,
//                    e
//            );
//            handleUnAuthorizedException(
//                    response,
//                    "Invalid UserName or Password"
//            );
//        }
//    }
//
//    protected boolean shouldNotFilter(HttpServletRequest request)
//            throws ServletException {
//        var isWhiteListed = AUTH_WHITELIST
//                .stream()
//                .anyMatch(it -> request.getServletPath().startsWith(it));
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//        return (
//                isWhiteListed ||
//                        disabledAuth
//                                .stream()
//                                .anyMatch(p -> {
//                                    var value = p.split(":");
//                                    return (
//                                            pathMatcher.match(value[1], request.getServletPath()) &&
//                                                    value[0].equals(request.getMethod())
//                                    );
//                                })
//        );
//    }
//
//    private void handleUnAuthorizedException(
//            HttpServletResponse response,
//            String message
//    ) throws IOException {
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        var out = response.getWriter();
//        var jsonResponse = new ResponseEntity<String>(
//                message,
//                null,
//                HttpStatus.UNAUTHORIZED
//        );
//        out.write(objectMapper.writeValueAsString(jsonResponse));
//        out.flush();
//    }
//}