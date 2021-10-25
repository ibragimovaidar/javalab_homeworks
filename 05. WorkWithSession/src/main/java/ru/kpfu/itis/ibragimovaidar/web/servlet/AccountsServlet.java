package ru.kpfu.itis.ibragimovaidar.web.servlet;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.ibragimovaidar.service.AccountService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "accountsServlet", urlPatterns = "/accounts")
public class AccountsServlet extends HttpServlet {

	private AccountService accountService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		ApplicationContext context =
				(ApplicationContext) servletContext.getAttribute(ApplicationContext.class.getName());
		accountService = context.getBean(AccountService.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("accounts", accountService.findAll());
	}
}
