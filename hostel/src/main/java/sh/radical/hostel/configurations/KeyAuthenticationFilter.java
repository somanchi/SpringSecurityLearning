package sh.radical.hostel.configurations;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class KeyAuthenticationFilter extends OncePerRequestFilter {

	List<String> disabledAuth = List.of(
		"GET:/v1/rooms",
		"GET:/v1/rooms/{\\d+}"
	);

	List<String> AUTH_WHITELIST = List.of(
		"/v3/api-docs",
		"/actuator",
		"/swagger-ui",
		"/swagger-ui.html",
		"/health"
	);

	@Value(value = "${authentication.key}")
	private String key;

	public void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		String apiKey = request.getHeader("X-API-KEY");
		if (apiKey == null || apiKey.isBlank() || !apiKey.equals(key))
		{
			handleUnAuthorizedException(response,"Invalid Key in header");
			return;
		}
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(key, key);
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
