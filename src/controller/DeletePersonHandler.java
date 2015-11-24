package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PersonService;

public class DeletePersonHandler {
	private PersonService personModel;
	
	public DeletePersonHandler(PersonService personModel){
		this.personModel=personModel;
	}
	public String handler(HttpServletRequest request, HttpServletResponse response){
		personModel.deletePerson(request.getParameter("id"));
		return new PersonOverviewHandler(personModel).handle(request, response);
	}
}
