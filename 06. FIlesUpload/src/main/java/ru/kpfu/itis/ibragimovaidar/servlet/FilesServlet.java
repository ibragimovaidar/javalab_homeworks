package ru.kpfu.itis.ibragimovaidar.servlet;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.ibragimovaidar.dto.FileDto;
import ru.kpfu.itis.ibragimovaidar.service.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@WebServlet(name = "filesServlet", urlPatterns = "/files")
@MultipartConfig
public class FilesServlet extends HttpServlet {

	private FileService fileService;

	@Override
	public void init(ServletConfig config) {
		ServletContext servletContext = config.getServletContext();
		ApplicationContext context =
				(ApplicationContext) servletContext.getAttribute(ApplicationContext.class.getName());
		fileService = context.getBean(FileService.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = request.getParameter("filename");
		FileDto fileDto = fileService.getByFilename(filename).orElseThrow(ServletException::new);

		response.setContentType(fileDto.getMimeType());
		response.setContentLength(fileDto.getSize());
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + fileDto.getOriginalFilename() + "\"");
		try (DataInputStream inputStream = new DataInputStream(fileDto.getFileInputStream());
			 DataOutputStream outputStream = new DataOutputStream(response.getOutputStream())) {
			byte[] bytes = new byte[inputStream.available()];
			inputStream.readFully(bytes);
			outputStream.write(bytes);
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("file");
		FileDto fileFormDto = FileDto.builder()
				.fileInputStream(filePart.getInputStream())
				.originalFilename(filePart.getSubmittedFileName())
				.size((int) filePart.getSize())
				.mimeType(filePart.getContentType())
				.build();
		fileService.upload(fileFormDto);
		response.setStatus(200);
	}
}
