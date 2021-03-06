package controller.persons;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CustomRedirectException;
import controller.RequestHandler;
import domain.ShopFacade;
import domain.person.Person;

public class LoginHandler implements RequestHandler {
	private ShopFacade shop;
	
	public LoginHandler(ShopFacade shop){
		this.shop = shop;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response) throws CustomRedirectException {
		if (request.getMethod().equals("GET")){
			return "persons/login.jsp";
		}
		ArrayList<String> errorMsg = new ArrayList<String>();
		String email = request.getParameter("email");
		String password =  request.getParameter("password");
		try{
			Person user = shop.authenticate(email, password);
			HttpSession session = request.getSession();
			session.setAttribute("person", user);
			request.setAttribute("sessionPerson", user);
			throw new CustomRedirectException("");
		}
		catch(CustomRedirectException e){
			throw e;
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
			request.setAttribute("errorMsg", errorMsg);
			return "persons/login.jsp";
		}
	}
}
