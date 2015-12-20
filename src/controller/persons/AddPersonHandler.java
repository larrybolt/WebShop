package controller.persons;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.RequestHandler;
import domain.ShopFacade;
import domain.person.Person;
import domain.person.PersonType;

public class AddPersonHandler implements RequestHandler {
	private ShopFacade shop;
	
	public AddPersonHandler(ShopFacade shop){
		this.shop = shop;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response) throws CustomRedirectException {
		if (request.getMethod().equals("GET")){
			return "persons/signUp.jsp";
		}
		ArrayList<String> errorMsg = new ArrayList<String>();
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String woonplaats = request.getParameter("woonplaats");
		PersonType type = PersonType.CUSTOMER;
		try{
			Person p = new Person(email, password,firstName, lastName, woonplaats, type);
			shop.addPerson(p);
			throw new CustomRedirectException("");
		}
		catch(CustomRedirectException e){
			throw e;
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
		}
		request.setAttribute("errorMsg", errorMsg);
		return "persons/signUp.jsp";
	}
}