package controller.products;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.RequestHandler;
import domain.ShopFacade;

public class DeleteProductHandler implements RequestHandler {
	private ShopFacade shop;
	
	public DeleteProductHandler(ShopFacade shop){
		this.shop = shop;
	}
	public String handle(HttpServletRequest request,HttpServletResponse response ){
		if (request.getMethod().equals("GET")) {
			request.setAttribute("product", shop.getProduct(request.getParameter("id")));
			return "products/confirmDelete.jsp";
		} 

		shop.removeProduct(Integer.parseInt(request.getParameter("id")));
		throw new CustomRedirectException("?action=products");
	}
}
