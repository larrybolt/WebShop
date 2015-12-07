package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.persons.AddPersonHandler;
import controller.persons.DeletePersonHandler;
import controller.persons.LoginHandler;
import controller.persons.LogoutHandler;
import controller.persons.PersonOverviewHandler;
import controller.products.AddProductHandler;
import controller.products.DeleteProductHandler;
import controller.products.ProductOverviewHandler;

import controller.CustomRedirectException;

import domain.NotAuthorizedException;
import domain.PersonService;
import domain.ProductService;

public class ControllerFactory {
	private Map<String, RequestHandler> handlers = new HashMap<>();
	
	
	public ControllerFactory(PersonService personModel, ProductService productModel) {
		// persons
		// management
		handlers.put("persons", new PersonOverviewHandler(personModel));
		handlers.put("addPerson", new AddPersonHandler(personModel));
		handlers.put("deletePerson", new DeletePersonHandler(personModel));
		// methods
		handlers.put("login", new LoginHandler(personModel));
		handlers.put("logout", new LogoutHandler());

		// products
		handlers.put("products", new ProductOverviewHandler(productModel));
		handlers.put("addProduct", new AddProductHandler(productModel));
		handlers.put("deleteProduct", new DeleteProductHandler(productModel));
	}
	public String handleAction(HttpServletRequest request,HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null) {
			return "index.jsp";
		}
		try{
			RequestHandler handler= handlers.get(action);
			return handler.handle(request,response);
		}
		catch (CustomRedirectException e){
			throw new CustomRedirectException(e.getLocation());
		}
		catch(NotAuthorizedException e ){
			List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "index.jsp";
		}
	}
}