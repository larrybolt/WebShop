package controller.products;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.RequestHandler;
import domain.ShopFacade;
import domain.product.Product;

public class AddProductHandler implements RequestHandler {
	
	private ShopFacade shop;
	
	public AddProductHandler(ShopFacade shop){
		this.shop = shop;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		if (request.getMethod().equals("GET")){
			return "products/create.jsp";
		}
		ArrayList<String> errorMsg = new ArrayList<String>();
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		String ImgUrl = request.getParameter("ImgUrl");
		try {
			shop.addProduct(new Product(name,price,ImgUrl));
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
			request.setAttribute("errorMsg", errorMsg);
			return "products/create.jsp";
		}
		throw new CustomRedirectException("?action=products");
	}
}