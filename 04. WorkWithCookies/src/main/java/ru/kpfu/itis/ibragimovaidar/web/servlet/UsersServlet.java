package ru.kpfu.itis.ibragimovaidar.web.servlet;

import ru.kpfu.itis.ibragimovaidar.util.JspUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet
public class UsersServlet extends HttpServlet {

	private static final List<String> USERS = Arrays.asList("user1", "user2", "user3");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", USERS);
		getServletContext().getRequestDispatcher(JspUtil.format("users"));
	}
}
