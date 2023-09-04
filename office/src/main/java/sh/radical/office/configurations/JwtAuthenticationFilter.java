package sh.radical.office.configurations;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import sh.radical.office.utils.JWTUtils;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


	@Autowired
	JWTUtils jwtUtils;

	List<String> disabledAuth = List.of(
			"GET:/v1/employees/{\\d+}",
			"GET:/v1/employees/"
	);

	List<String> AUTH_WHITELIST = List.of(
		"/v3/api-docs",
		"/actuator",
		"/swagger-ui",
		"/swagger-ui.html",
		"/health"
	);

	public void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		String jwtHeader = request.getHeader("Authorization");
		if (jwtHeader == null || jwtHeader.isBlank() || !jwtHeader.startsWith("Bearer ")) {
			handleUnAuthorizedException(response, "Invalid Authorization Header");
			return;
		}
		var token = jwtHeader.split(" ")[1];
		var authRequest = jwtUtils.validateJwt(token);
		if (authRequest == null) {
			handleUnAuthorizedException(response ,"Failed to validate token");
			return;
		}
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authRequest);
		filterChain.doFilter(request, response);
	}

	protected boolean shouldNotFilter(HttpServletRequest request)
		throws ServletException {
		var isWhiteListed = AUTH_WHITELIST
			.stream()
			.anyMatch(it -> request.getServletPath().startsWith(it));
		AntPathMatcher pathMatcher = new AntPathMatcher();
		return (
			isWhiteListed ||
			disabledAuth
				.stream()
				.anyMatch(p -> {
					var value = p.split(":");
					return (
						pathMatcher.match(value[1], request.getServletPath()) &&
						value[0].equals(request.getMethod())
					);
				})
		);
	}

	private void handleUnAuthorizedException(
		HttpServletResponse response,
		String message
	) throws IOException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(message);
	}
}
