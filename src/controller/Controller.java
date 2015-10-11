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
import domain.Product;
import domain.ProductService;

@WebServlet("/Controller")	
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonService persons;
	private ProductService products;
       
	public Controller() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		products = new ProductService(config.getServletContext().getResourceAsStream("/WEB-INF/config.xml"));
		persons = new PersonService(config.getServletContext().getResourceAsStream("/WEB-INF/config.xml"));
		// using maps instead db
		//products = new ProductService();
		//persons = new PersonService();
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

		if (action == null) {
			request.getRequestDispatcher(destination).forward(request, response);
			return;
		}

		if(action.equals("overview")){
			destination = showPersons(request,response);
		}
		if(action.equals("products")){
			destination = showProducts(request,response);
		}
		if(action.equals("add")){
			destination = addPerson(request,response);
		}
		if(action.equals("addProduct")){
			if (request.getMethod().equals("GET")) {
				request.getRequestDispatcher("products/create.jsp").forward(request, response);
				return;
			} else {
				destination = addProduct(request,response);
			}
		}
		if(action.equals("editProduct")){
			if (request.getMethod().equals("GET")) {
				request.setAttribute("product", products.getProduct(request.getParameter("id")));
				request.getRequestDispatcher("products/edit.jsp").forward(request, response);
				return;
			} else {
				destination = editProduct(request,response);
			}
		}
		if(action.equals("signUp")){
			destination = "persons/signUp.jsp";
		}
		if(action.equals("deleteProduct")){
			if (request.getMethod().equals("GET")) {
				request.setAttribute("product", products.getProduct(request.getParameter("id")));
				request.getRequestDispatcher("products/confirmDelete.jsp").forward(request, response);
				return;
			} else {
				destination = deleteProduct(request,response);
			}
		}
		if(action.equals("deletePerson")){
			if (request.getMethod().equals("GET")) {
				request.setAttribute("person", persons.getPerson(request.getParameter("id")));
				request.getRequestDispatcher("persons/confirmDelete.jsp").forward(request, response);
				return;
			} else {
				destination = deletePerson(request,response);
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}
	

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		products.deleteProduct(request.getParameter("id"));
		return showProducts(request,response);
	}

	private String addProduct(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<String> errorMsg = new ArrayList<String>();
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		String ImgUrl = request.getParameter("ImgUrl");
		try{
			products.addProduct(new Product(name,price,ImgUrl));
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
			request.setAttribute("errorMsg", errorMsg);
			return "products/create.jsp";
		}
		return showProducts(request,response);
	}

	private String deletePerson(HttpServletRequest request, HttpServletResponse response) {
		persons.deletePerson(request.getParameter("id"));
		return this.showPersons(request, response);
	}

	private String addPerson(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<String> errorMsg = new ArrayList<String>();
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try{
			Person p = new Person(email,password,firstName,lastName);
			persons.addPerson(p);
			return this.showPersons(request, response);
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
		}
		
		if(errorMsg.size()==0){
			return showPersons(request, response);
		}
		request.setAttribute("errorMsg", errorMsg);
		return "persons/signUp.jsp";
	}

	private String editProduct(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<String> errorMsg = new ArrayList<String>();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String ImgUrl = request.getParameter("ImgUrl");
		try {
			Double price = Double.parseDouble(request.getParameter("price"));
			try{
				Product p = products.getProduct(id);
				p.setName(name);
				p.setPrice(price);
				p.setImgUrl(ImgUrl);
				products.updateProducts(p);
			}
			catch(Exception e){
				errorMsg.add(e.getMessage());
			}
		} catch(Exception e){
			errorMsg.add(e.getMessage());
		}
		
		if(errorMsg.size()==0){
			return showProducts(request, response);
		}
		request.setAttribute("errorMsg", errorMsg);
		return "products/create.jsp";
	}
	private String showPersons(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("persons",persons.getPersons() );
		return "persons/overview.jsp";
	}
	private String showProducts(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("products",products.getProducts());
		return "products/overview.jsp";
	}
}

	
