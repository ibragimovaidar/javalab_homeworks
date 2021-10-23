package ru.kpfu.itis.ibragimovaidar.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

	private static final String AUTHENTICATION_COOKIE_NAME = "AUTHENTICATED";
	private static final String AUTHENTICATED_COOKIE_VALUE = "TRUE";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		boolean isClientAuthenticated = false;
		for (Cookie cookie : cookies) {
			if (AUTHENTICATION_COOKIE_NAME.equalsIgnoreCase(cookie.getName())
					&& AUTHENTICATED_COOKIE_VALUE.equalsIgnoreCase(cookie.getValue())
			){
				isClientAuthenticated = true;
			}
		}

		if (isClientAuthenticated){
			chain.doFilter(request,response);
		} else {
			Cookie cookie = new Cookie(AUTHENTICATION_COOKIE_NAME, "FALSE");
			((HttpServletResponse) response).addCookie(cookie);
			((HttpServletResponse) response).sendError(401);
		}
	}
}

