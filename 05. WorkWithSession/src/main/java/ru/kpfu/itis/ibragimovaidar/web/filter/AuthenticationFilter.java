package ru.kpfu.itis.ibragimovaidar.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile", "/accounts"})
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if (session == null || session.isNew() || session.getAttribute("isAuthenticated") == null) {
			((HttpServletResponse) response).sendRedirect("/signIn");
			return;
		}
		chain.doFilter(request, response);
	}
}
