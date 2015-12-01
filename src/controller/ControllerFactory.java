package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.ServiceException;

import domain.NotAuthorizedException;
import domain.PersonService;
import domain.PersonType;
import domain.ProductService;

public class ControllerFactory {
	private Map<String, RequestHandler> handlers = new HashMap<>();
	
	
	public ControllerFactory(PersonService personModel, ProductService productModel ){
		handlers.put("overview", new PersonOverviewHandler(personModel));
		handlers.put("products", new ProductOverviewHandler(productModel));
		handlers.put("add", new AddPersonHandler(personModel));
		handlers.put("addProduct", new AddPersonHandler(personModel));
		handlers.put("signUp", new SignUpHandler());
	}
	public String handleAction(HttpServletRequest request,HttpServletResponse response){
		String action = request.getParameter("action");
		if (action == null) {
			return"index.jsp";
		}
		try{
			RequestHandler handler= handlers.get(action);
			return handler.handle(request,response);
			
		}
		catch(NotAuthorizedException e ){
			List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "index.jsp";
		}
		
	}
	
	
	
	
	
}
