package ru.kpfu.itis.ibragimovaidar.servlet;

import ru.kpfu.itis.ibragimovaidar.helper.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "filesFormServlet", urlPatterns = "/upload")
public class FilesFormServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(JspHelper.resolveJsp("filesForm")).forward(request, response);
	}
}
