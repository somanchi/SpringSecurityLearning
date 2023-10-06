package sh.radical.selectallinteger.configurations;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sh.radical.selectallinteger.configurations.MethodArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public void addArgumentResolvers(
		List<HandlerMethodArgumentResolver> argumentResolvers
	) {
		argumentResolvers.add(new MethodArgumentResolver());
	}
}
