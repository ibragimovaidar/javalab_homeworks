package ru.kpfu.itis.ibragimovaidar.web.servlet;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.ibragimovaidar.dto.AccountDTO;
import ru.kpfu.itis.ibragimovaidar.service.AccountService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static ru.kpfu.itis.ibragimovaidar.util.JspResolverUtil.resolveJsp;

@WebServlet(name = "profileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

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
		HttpSession session = request.getSession(false);
		if (session == null || session.isNew() || session.getAttribute("email") == null){
			response.sendError(401);
			return;
		}
		String email = (String) session.getAttribute("email");
		Optional<AccountDTO> accountDTOOptional = accountService.findByEmail(email);
		if (!accountDTOOptional.isPresent()){
			response.sendError(500);
			return;
		}
		request.setAttribute("accountDTO", accountDTOOptional.get());
		request.getRequestDispatcher(resolveJsp("profile")).forward(request, response);
	}
}
