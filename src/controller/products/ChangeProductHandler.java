package controller.products;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.RequestHandler;
import domain.ShopFacade;
import domain.product.Product;

public class ChangeProductHandler implements RequestHandler{
	private ShopFacade shop;
	
	public ChangeProductHandler(ShopFacade shop){
		this.shop = shop;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		if (request.getMethod().equals("GET")) {
			request.setAttribute("product", shop.getProduct(request.getParameter("id")));
			return "products/edit.jsp";
		} 
		ArrayList<String> errorMsg = new ArrayList<String>();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String ImgUrl = request.getParameter("ImgUrl");
		try {
			Double price = Double.parseDouble(request.getParameter("price"));
			try{
				Product p = shop.getProduct(id);
				p.setName(name);
				p.setPrice(price);
				p.setImgUrl(ImgUrl);
				shop.updateProducts(p);
			}
			catch(Exception e){
				errorMsg.add(e.getMessage());
			}
		} catch(Exception e){
			errorMsg.add(e.getMessage());
		}
		throw new CustomRedirectException("?action=products");
	}
}