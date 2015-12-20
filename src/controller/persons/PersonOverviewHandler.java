package controller.persons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.RequestHandler;
import domain.ShopFacade;

public class PersonOverviewHandler implements RequestHandler {
	private ShopFacade shop;
	public PersonOverviewHandler(ShopFacade shop){
		this.shop = shop;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("persons", shop.getPersons());
		return "persons/overview.jsp";
	}
}