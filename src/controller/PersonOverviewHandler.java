package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PersonService;

public class PersonOverviewHandler implements RequestHandler {
	private PersonService personModel;
	public PersonOverviewHandler(PersonService personModel){
		this.personModel = personModel;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("persons",personModel.getPersons() );
		return "persons/overview.jsp";
	}
	
}
