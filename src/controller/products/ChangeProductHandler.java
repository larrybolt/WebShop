package controller.products;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomRedirectException;
import controller.RequestHandler;
import domain.Product;
import domain.ProductService;

public class ChangeProductHandler implements RequestHandler{
	private ProductService productModel;
	
	public ChangeProductHandler(ProductService productModel){
		this.productModel = productModel;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
		if (request.getMethod().equals("GET")) {
			request.setAttribute("product", productModel.getProduct(request.getParameter("id")));
			return "products/edit.jsp";
		} 
		ArrayList<String> errorMsg = new ArrayList<String>();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String ImgUrl = request.getParameter("ImgUrl");
		try {
			Double price = Double.parseDouble(request.getParameter("price"));
			try{
				Product p = productModel.getProduct(id);
				p.setName(name);
				p.setPrice(price);
				p.setImgUrl(ImgUrl);
				productModel.updateProducts(p);
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