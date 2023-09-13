package com.FunFinder360.Controller.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Controller.HomeController;
import com.FunFinder360.Controller.SuperClass;

public class MeberLogoutController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		session.removeAttribute("loginfo");
		new HomeController().doGet(request, response);
	}
}
