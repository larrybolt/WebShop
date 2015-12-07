package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestHandler {
	
	public String handle(HttpServletRequest request, HttpServletResponse response) throws CustomRedirectException;
}
