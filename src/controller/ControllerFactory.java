package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.persons.*;
import controller.products.*;

import domain.NotAuthorizedException;
import domain.ShopFacade;

public class ControllerFactory {
	private Map<String, RequestHandler> handlers = new HashMap<>();
	
	
	public ControllerFactory(ShopFacade shop) {
		// persons
		// management
		handlers.put("persons", new PersonOverviewHandler(shop));
		handlers.put("addPerson", new AddPersonHandler(shop));
		handlers.put("deletePerson", new DeletePersonHandler(shop));
		// methods
		handlers.put("login", new LoginHandler(shop));
		handlers.put("logout", new LogoutHandler());

		// products
		handlers.put("products", new ProductOverviewHandler(shop));
		handlers.put("addProduct", new AddProductHandler(shop));
		handlers.put("deleteProduct", new DeleteProductHandler(shop));
		handlers.put("editProduct", new ChangeProductHandler(shop));
	}
	public String handleAction(HttpServletRequest request, HttpServletResponse response) {
		// if no action parameter, just return index
		if (!request.getParameterMap().containsKey("action")){
			return "index.jsp";
		}
		String action = request.getParameter("action");
		if (action == null || action.length() == 0) {
			// is action parameter exists, but is empty remove from path
			throw new CustomRedirectException("");
		}
		try{
			RequestHandler handler = handlers.get(action);
			return handler.handle(request,response);
		}
		catch (CustomRedirectException e){
			throw new CustomRedirectException(e.getLocation());
		}
		catch (NullPointerException e){
			return "404.jsp";
		}
		catch(NotAuthorizedException e ){
			List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "index.jsp";
		}
	}
}