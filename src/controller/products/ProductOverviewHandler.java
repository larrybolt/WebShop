package controller.products;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RequestHandler;
import domain.ShopFacade;

public class ProductOverviewHandler implements RequestHandler{
	
	private ShopFacade shop;
	
	public ProductOverviewHandler(ShopFacade shop){
		this.shop = shop;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		if (request.getParameter("order") != null && request.getParameter("order").equals("price"))
			request.setAttribute("products", shop.getProductsOrderByPrice());
		else
			request.setAttribute("products", shop.getProducts());

		return "products/overview.jsp";
	}
	
	
}
