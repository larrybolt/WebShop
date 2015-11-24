package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductService;

public class ProductOverviewHandler implements RequestHandler{
	
	private ProductService productModel;
	
	public ProductOverviewHandler(ProductService productModel){
		this.productModel = productModel;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		if (request.getParameter("order") != null && request.getParameter("order").equals("price"))
			request.setAttribute("products",productModel.getProductsOrderByPrice());
		else
			request.setAttribute("products",productModel.getProducts());

		return "products/overview.jsp";
	}
	
	
}
