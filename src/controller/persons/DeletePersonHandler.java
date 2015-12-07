package controller.persons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.RequestHandler;
import domain.PersonService;

public class DeletePersonHandler implements RequestHandler {
	private PersonService personModel;
	
	public DeletePersonHandler(PersonService personModel){
		this.personModel = personModel;
	}
	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response){
		if (request.getMethod().equals("GET")){
			request.setAttribute("person", personModel.getPerson(request.getParameter("id")));
			return "persons/confirmDelete.jsp";
		}
		personModel.deletePerson(request.getParameter("id"));
		throw new CustomRedirectException("?action=persons");
	}
}
