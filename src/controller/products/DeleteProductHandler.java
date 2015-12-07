package controller.products;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RequestHandler;
import domain.ProductService;

public class DeleteProductHandler implements RequestHandler {
	private ProductService productModel;
	
	public DeleteProductHandler(ProductService productModel){
		this.productModel=productModel;
	}
	public String handle(HttpServletRequest request,HttpServletResponse response ){
		if (request.getMethod().equals("GET")) {
			request.setAttribute("product", productModel.getProduct(request.getParameter("id")));
			return "products/confirmDelete.jsp";
		} 

		productModel.deleteProduct(request.getParameter("id"));
		return new ProductOverviewHandler(productModel).handle(request, response);
	}
}
