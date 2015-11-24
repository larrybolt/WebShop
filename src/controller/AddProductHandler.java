package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import domain.ProductService;

public class AddProductHandler {
	
	private ProductService productModel;
	
	public AddProductHandler(ProductService productModel){
		this.productModel = productModel;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		ArrayList<String> errorMsg = new ArrayList<String>();
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		String ImgUrl = request.getParameter("ImgUrl");
		try{
			productModel.addProduct(new Product(name,price,ImgUrl));
		}
		catch(Exception e){
			errorMsg.add(e.getMessage());
			request.setAttribute("errorMsg", errorMsg);
			return "products/create.jsp";
		}
		return new ProductOverviewHandler(productModel).handle(request, response);
	}

}
