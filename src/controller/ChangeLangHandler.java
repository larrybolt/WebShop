package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;

import domain.ShopFacade;

public class ChangeLangHandler implements RequestHandler {
	private ShopFacade sf = null;
	
	public ChangeLangHandler(ShopFacade sf) {
		this.sf = sf;
	}

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws CustomRedirectException {
		response.addCookie(new Cookie("lang", request.getParameter("l")));
		throw new CustomRedirectException("");
		//return "index.jsp";
	}

}
