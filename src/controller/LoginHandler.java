package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Person;
import domain.PersonService;

public class LoginHandler implements RequestHandler {
	private PersonService personModel;
	
	public LoginHandler(PersonService personModel){
		this.personModel= personModel;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		ArrayList<String> errorMsg = new ArrayList<String>();
		String email = request.getParameter("email");
		String password =  request.getParameter("password");
		try{
			Person user = personModel.authenticate(email, password);
			HttpSession session = request.getSession();
			session.setAttribute("person", user);
			request.setAttribute("sessionPerson", user);
			return "index.jsp";
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
			request.setAttribute("errorMsg", errorMsg);
			return "persons/login.jsp";
		}
	}
}
