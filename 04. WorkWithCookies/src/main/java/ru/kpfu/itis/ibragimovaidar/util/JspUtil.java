package ru.kpfu.itis.ibragimovaidar.util;

public final class JspUtil {

	private static final String JSP_FOLDER = "/WEB-INF/jsp/";

	public static String format(String name){
		return JSP_FOLDER + name + ".jsp";
	}

	private JspUtil() {
		throw new RuntimeException();
	}
}
