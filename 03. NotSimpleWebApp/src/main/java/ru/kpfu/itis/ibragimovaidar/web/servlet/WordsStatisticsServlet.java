package ru.kpfu.itis.ibragimovaidar.web.servlet;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.ibragimovaidar.common.exception.ServiceException;
import ru.kpfu.itis.ibragimovaidar.config.AppConfig;
import ru.kpfu.itis.ibragimovaidar.model.WordsStatistics;
import ru.kpfu.itis.ibragimovaidar.service.WordsStatisticsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "wordsStatisticsServlet", urlPatterns = "/words")
public class WordsStatisticsServlet extends HttpServlet {

	private WordsStatisticsService wordsStatisticsService;

	@Override
	public void init(ServletConfig servletConfig) {
		ApplicationContext context = (ApplicationContext) servletConfig.getServletContext()
				.getAttribute(AppConfig.APPLICATION_CONTEXT_SERVLET_CONTEXT_ATTRIBUTE_NAME);
		wordsStatisticsService = context.getBean(WordsStatisticsService.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/wordsStatisticsForm.jsp").forward(request, response);
	}

	private static final String FOLDER_PARAMETER_NAME = "folder";

	private static final String LIMIT_PARAMETER_NAME = "limit";

	private static final int DEFAULT_LIMIT = 10;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folder = request.getParameter(FOLDER_PARAMETER_NAME);
		if (folder == null){
			throw new ServletException("Folder parameter mustn't be empty");
		}
		int limit = request.getParameter(LIMIT_PARAMETER_NAME) != null ?
				Integer.parseInt(request.getParameter("limit")) : DEFAULT_LIMIT;

		List<WordsStatistics> wordsStatisticsList = null;
		try {
			 wordsStatisticsList = wordsStatisticsService.getMostFrequentWordsLimit(folder, limit);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

		request.setAttribute("wordsStatisticsList", wordsStatisticsList);
		request.getRequestDispatcher("/WEB-INF/jsp/wordsStatisticsResult.jsp").forward(request, response);
	}
}
