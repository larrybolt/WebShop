package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutHandler {

	public LogoutHandler(){
		
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("person", null);
		request.setAttribute("sessionPerson", null);
		return "index.jsp";
	}
	
	
	
}
