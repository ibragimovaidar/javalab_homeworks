package ru.kpfu.itis.ibragimovaidar.web.servlet;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.ibragimovaidar.dto.SignUpForm;
import ru.kpfu.itis.ibragimovaidar.service.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.kpfu.itis.ibragimovaidar.util.JspResolverUtil.resolveJsp;

@WebServlet(name = "signUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

	private SignUpService signUpService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		ApplicationContext context =
				(ApplicationContext) servletContext.getAttribute(ApplicationContext.class.getName());
		signUpService = context.getBean(SignUpService.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(resolveJsp("signUp")).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignUpForm signUpForm = SignUpForm.builder()
				.firstName(request.getParameter("firstName"))
				.lastName(request.getParameter("lastName"))
				.email(request.getParameter("email"))
				.password(request.getParameter("password"))
				.build();
		signUpService.signUp(signUpForm);
		response.sendRedirect("/signIn");
	}
}
