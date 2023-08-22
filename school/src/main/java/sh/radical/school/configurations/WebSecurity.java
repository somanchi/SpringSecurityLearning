package sh.radical.school.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/actuator/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/health",
    };


    @Autowired
    private BasicAuthenticationFilter basicAuthenticationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.GET,"/v1/employees").permitAll()
                .antMatchers(HttpMethod.GET,"/v1/employees/{\\d+}").permitAll()
                .and()
                .httpBasic()
                .disable()
                .formLogin()
                .disable()
                .addFilterBefore(basicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}