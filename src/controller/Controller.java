package controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.PersonService;

@WebServlet("/Controller")	
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonService ps= new PersonService();
       
    public Controller() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
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
		String destination = "index.jsp";
		String action = request.getParameter("action");
		if (action == null)
			request.getRequestDispatcher(destination).forward(request, response);

		if(action.equals("overview")){
			destination = showPersons(request,response);
		}
		if(action.equals("add")){
			destination = addPerson(request,response);
		}
		if(action.equals("delete")){
			destination = deletePerson(request,response);
		}
		if(action.equals("signUp")){
			destination = "signUp.jsp";
		}
		/*if(action.equals("deleteConfirmed")){
			destination = deletePersonConfirmed(request,response);
		}*/
		/*if(action.equals("find")){
			destination = findPerson(request,response);
		}*/
		
		RequestDispatcher view = request.getRequestDispatcher(destination);

		view.forward(request, response);
		
	}
	

	private String deletePerson(HttpServletRequest request, HttpServletResponse response) {
		ps.deletePerson(request.getParameter("email"));
		return "overview.jsp";
	}

	private String addPerson(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<String> errorMsg = new ArrayList<String>();
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try{
			Person p = new Person(email,password,firstName,lastName);
			ps.addPerson(p);
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
		}
		
		if(errorMsg.size()==0){
			return showPersons(request, response);
		}
		request.setAttribute("errorMsg", errorMsg);
		return "signUp.jsp";
	}

	private String showPersons(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("persons",ps.getPersons() );
		return "overview.jsp";
	}
}

	
