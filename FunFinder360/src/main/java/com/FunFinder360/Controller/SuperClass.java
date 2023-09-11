package com.FunFinder360.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuperClass implements SuperController {
	 private HttpServletRequest request;
	 private HttpServletResponse response;
	 protected HttpSession session;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	public void goToPage(String path) {
		if(this.request == null) {
			System.out.print("doGet / doPost method call omission");
		}
		RequestDispatcher dispatcher = null;
		try {
			dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
