package sh.radical.hostel.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	@Autowired
	KeyAuthenticationFilter filter;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf()
			.disable()
			.httpBasic()
			.disable()
			.formLogin()
			.disable()
			.addFilterBefore(
				filter,
				UsernamePasswordAuthenticationFilter.class
			);

		return http.build();
	}
}
