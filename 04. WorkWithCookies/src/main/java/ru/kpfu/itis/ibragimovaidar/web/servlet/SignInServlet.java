package ru.kpfu.itis.ibragimovaidar.web.servlet;

import ru.kpfu.itis.ibragimovaidar.AuthenticationService;
import ru.kpfu.itis.ibragimovaidar.util.JspUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

	private AuthenticationService authenticationService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		authenticationService = (AuthenticationService) servletContext.getAttribute(AuthenticationService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(JspUtil.format("loginPage")).forward(request, response);
	}

	private static final String USERNAME_PARAM = "username";
	private static final String PASSWORD_PARAM = "password";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter(USERNAME_PARAM);
		String password = request.getParameter(PASSWORD_PARAM);
		if (authenticationService.authenticate(username, password)){
			Cookie authenticatedCookie = new Cookie("AUTHENTICATED", "TRUE");
			response.addCookie(authenticatedCookie);
			response.sendRedirect("/users");
			return;
		}
		response.sendError(400);
	}
}
