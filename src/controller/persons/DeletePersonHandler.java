package controller.persons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.RequestHandler;
import domain.ShopFacade;

public class DeletePersonHandler implements RequestHandler {
	private ShopFacade shop;
	
	public DeletePersonHandler(ShopFacade shop){
		this.shop = shop;
	}
	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response){
		if (request.getMethod().equals("GET")){
			request.setAttribute("person", shop.getPerson(Integer.parseInt(request.getParameter("id"))));
			return "persons/confirmDelete.jsp";
		}
		shop.deletePerson(request.getParameter("id"));
		throw new CustomRedirectException("?action=persons");
	}
}
