package controller;

import javax.servlet.http.HttpServletRequest;

import domain.PersonType;

public class SignUpHandler {
	
	public SignUpHandler(){
		
	}
	public String handler(HttpServletRequest request, PersonType type){
		return "persons/signUp.jsp";
	}
}
