package ru.kpfu.itis.ibragimovaidar.util;

public class JspResolverUtil {

	private static final String JSP_FOLDER = "/WEB-INF/jsp/";

	public static String resolveJsp(String name){
		return JSP_FOLDER + name + ".jsp";
	}

	private JspResolverUtil() {
		throw new AssertionError();
	}
}
