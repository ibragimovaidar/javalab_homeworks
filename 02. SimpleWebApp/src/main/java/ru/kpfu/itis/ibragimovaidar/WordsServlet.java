package ru.kpfu.itis.ibragimovaidar;

import ru.kpfu.itis.ibragimovaidar.commons.exceptions.ServiceException;
import ru.kpfu.itis.ibragimovaidar.model.StatisticsRecord;
import ru.kpfu.itis.ibragimovaidar.service.WordsStatisticsService;
import ru.kpfu.itis.ibragimovaidar.service.WordsStatisticsServiceImpl;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name="wordsServlet", urlPatterns = "/words")
public class WordsServlet extends HttpServlet {

	private final WordsStatisticsService wordsStatisticsService = new WordsStatisticsServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter writer = resp.getWriter()){
			writer.write("<html>");
			writer.write("<head>");
			writer.write("<title>Words statistics service</title>");
			writer.write("</head>");
			writer.write("<body>");
			writer.write("<form action=\"" +
					req.getRequestURI() +
					"\"method=\"post\">");
			writer.write("<label for=\"folder\">folder absolute path:</label><br>");
			writer.write("<input type=\"text\" id=\"folder\" name=\"folder\"><br>");
			writer.write("<label for=\"limit\">top words limit</label><br>");
			writer.write("<input type=\"text\" id=\"limit\" name=\"limit\"><br>");
			writer.write("  <input type=\"submit\" value=\"Submit\">");
			writer.write("</form>");
			writer.write("</body>");
			writer.write("</html>");
		}
	}

	private static final int DEFAULT_WORDS_LIMIT = 10;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folder") == null){
			resp.sendError(400, "folder parameter not found");
			return;
		}
		String folderAbsolutePath = req.getParameter("folder");
		int limit = req.getParameter("limit") != null ?
				Integer.parseInt(req.getParameter("limit")) : DEFAULT_WORDS_LIMIT;

		try (PrintWriter writer = resp.getWriter()){
			List<StatisticsRecord> records = wordsStatisticsService.getMostFrequentWordsLimit(folderAbsolutePath, limit);
			for (StatisticsRecord record: records){
				writer.write("<p>filename: " + record.getFilename() + " </p>");
				writer.write("<br>");
				writer.write("<table>");
				writer.write("  <tr>\n" +
						"    <td>WORD</td>\n" +
						"    <td>VALUE</td>\n" +
						"  </tr>");
				record.getWordValueMap().forEach((key, value) -> {
					writer.write("  <tr>\n" +
							"    <td>"+ key + "</td>\n" +
							"    <td>" + value +"</td>\n" +
							"  </tr>");
				});
				writer.write("</table>");
			}
		} catch (ServiceException | IOException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
