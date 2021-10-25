package ru.kpfu.itis.ibragimovaidar.web.servlet;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.ibragimovaidar.dto.SignInForm;
import ru.kpfu.itis.ibragimovaidar.service.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.kpfu.itis.ibragimovaidar.util.JspResolverUtil.resolveJsp;

@WebServlet(name = "signInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

	private SignInService signInService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		ApplicationContext context =
				(ApplicationContext) servletContext.getAttribute(ApplicationContext.class.getName());
		signInService = context.getBean(SignInService.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(resolveJsp("signIn")).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignInForm signInForm = SignInForm.builder()
				.email(request.getParameter("email"))
				.password(request.getParameter("password"))
				.build();
		if (signInService.signIn(signInForm)){
			HttpSession session = request.getSession(true);
			session.setAttribute("isAuthenticated", true);
			session.setAttribute("email", signInForm.getEmail());
			response.sendRedirect("/profile");
		} else {
			response.sendRedirect("/signIn?error");
		}
	}
}
