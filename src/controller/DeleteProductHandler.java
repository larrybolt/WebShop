package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductService;

public class DeleteProductHandler {
	private ProductService productModel;
	
	public DeleteProductHandler(ProductService productModel){
		this.productModel=productModel;
	}
	public String handle(HttpServletRequest request,HttpServletResponse response ){
		if (request.getMethod().equals("GET")) {
			request.setAttribute("product", productModel.getProduct(request.getParameter("id")));
			return "products/confirmDelete.jsp";
		} else {
			productModel.deleteProduct(request.getParameter("id"));
			return  new ProductOverviewHandler(productModel).handle(request, response);
		}
	}
}
