package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PersonType;

public class SignUpHandler implements RequestHandler {
	
	public SignUpHandler(){
		
	}
	public String handle(HttpServletRequest request, HttpServletResponse response) {
		return "persons/signUp.jsp";
	}
}
