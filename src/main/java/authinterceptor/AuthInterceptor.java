package authinterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ptithcm.constant.SystemConstant;
import ptithcm.model.customer.Customer;
import ptithcm.model.user.User;
import ptithcm.util.SessionUtil;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handle");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Auth authAnnotation = handlerMethod.getMethod().getAnnotation(Auth.class);
		System.out.println(authAnnotation);

		if (request.getRequestURI().startsWith(request.getContextPath() + "/admin")
				&& request.getSession().getAttribute(SystemConstant.Model.USER_MODEL) == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login.htm");
			return false;
		}
		
		if ((request.getRequestURI().startsWith(request.getContextPath() + "/e-commerce")
				|| request.getRequestURI().startsWith(request.getContextPath() + "/customer"))
				&& request.getSession().getAttribute(SystemConstant.Model.CUSTOMER_MODEL) == null) {
			response.sendRedirect(request.getContextPath() + "/e-commerce/login.htm");
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// This method is called after the controller method is invoked but before the
		// view is rendered
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// This method is called after the view is rendered, allowing for resource
		// cleanup
	}
}
