package ru.kpfu.itis.ibragimovaidar.helper;

public final class JspHelper {

	private static final String JSP_PATH = "/WEB-INF/jsp/";

	public static String resolveJsp(String pageName){
		return JSP_PATH + pageName + ".jsp";
	};

	private JspHelper(){
		throw new RuntimeException();
	}
}
