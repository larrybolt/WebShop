package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import controller.CustomRedirectException;

import domain.NotAuthorizedException;
import domain.ShopFacade;
import domain.person.Person;

@WebServlet("/Controller")	
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShopFacade shop;
       
	public Controller() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		shop = new ShopFacade(config.getServletContext().getResourceAsStream("/WEB-INF/config.xml"));
		// using maps instead db
		//shop = new ShopFacade();
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}
	protected void procesRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		request.setAttribute("sessionPerson", person);
		Cookie c = null;
		int visits = 0;
		boolean cookieFound = false;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i=0; i<cookies.length;i++){
				if(cookies[i].getName().equals("counter")){
					try {
						visits = Integer.parseInt(cookies[i].getValue())+1;
						cookies[i].setValue(new Integer(visits).toString());
					} catch (Exception e){ // catch parseInt errors
						cookies[i].setValue("1");
					}
					c = cookies[i];
					cookieFound = true;
				}
				if(cookies[i].getName().equals("lang")){
					request.setAttribute("language", cookies[i].getValue());
				}
			} 
		}	
		if(!cookieFound){
			c = new Cookie("counter","1" );
		}
		
		response.addCookie(c);
		request.setAttribute("counter", c.getValue());
		String destination;
		try {
			destination = new ControllerFactory(shop).handleAction(request, response);
			RequestDispatcher view = request.getRequestDispatcher(destination);
			view.forward(request, response);
		} catch (CustomRedirectException redirect) {
			response.sendRedirect(redirect.getLocation());
		}
	}
	private void SessionHandler(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
	}
	

	/*private boolean isFromUserWithRole(HttpServletRequest request, PersonType type) {
		return true;
		/* dit even allemaal skippen voor de test van web
		Person person = (Person) request.getSession().getAttribute("user");
	 	 if (person != null && person.getPersonType().equals(type)) {
			 return true;
	 	 }
	 	 return false;
	 	 */
	

	
	
	
}