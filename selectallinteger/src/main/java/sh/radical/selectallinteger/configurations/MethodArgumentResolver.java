package sh.radical.selectallinteger.configurations;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sh.radical.selectallinteger.entities.Context;
import sh.radical.selectallinteger.entities.UserContext;

public class MethodArgumentResolver implements HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameter().getType().equals(Context.class);
	}

	public Object resolveArgument(
		MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) throws Exception {
		Context context = new Context();
		UserContext userContext = new UserContext("anonymous");
		context.setUserContext(userContext);
		context.setAuthenticated(false);
		return context;
	}
}
