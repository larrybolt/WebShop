package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import domain.ProductService;

public class editProductHandler implements RequestHandler{
	private ProductService productModel;
	
	public editProductHandler(ProductService productModel){
		this.productModel = productModel;
	}
	public String handle(HttpServletRequest request, HttpServletResponse response){
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
		
		if(errorMsg.size()==0){
			return new ProductOverviewHandler(productModel).handle(request, response);
		}
		request.setAttribute("errorMsg", errorMsg);
		return "products/create.jsp";
	}
	
}
