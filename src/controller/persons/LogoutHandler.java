package controller.persons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.RequestHandler;

public class LogoutHandler implements RequestHandler {

	public LogoutHandler(){
		
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("person", null);
		request.setAttribute("sessionPerson", null);
		throw new CustomRedirectException("");
	}
}