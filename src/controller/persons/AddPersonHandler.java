package controller.persons;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RequestHandler;
import domain.Person;
import domain.PersonService;
import domain.PersonType;

public class AddPersonHandler implements RequestHandler {
	private PersonService personModel;
	
	public AddPersonHandler(PersonService personModel){
		this.personModel = personModel;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
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
			personModel.addPerson(p);
			return new PersonOverviewHandler(personModel).handle(request, response);
			
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
		}
		if(errorMsg.size()==0){
			return new PersonOverviewHandler(personModel).handle(request, response);
		}
		request.setAttribute("errorMsg", errorMsg);
		return "persons/signUp.jsp";
	}
}