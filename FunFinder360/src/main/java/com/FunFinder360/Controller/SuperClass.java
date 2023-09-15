package com.FunFinder360.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FunFinder360.Bean.Model.MemberPersonalUser;

public class SuperClass implements SuperController {
	 private HttpServletRequest request;
	 private HttpServletResponse response;
	 protected HttpSession session;
	
	 protected MemberPersonalUser logInfo = null;
	 
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.logInfo = (MemberPersonalUser) session.getAttribute("loginfo");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.logInfo = (MemberPersonalUser) session.getAttribute("loginfo");
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
	
	public String getUrlInfomation(String todoCommmand) {
		String appName = this.request.getContextPath() ;
		String mappingName = "/FunFinder360" ;
		String text = appName + mappingName + "?command=" + todoCommmand ;
		return text ;
	}
	
	public void setAlertMessage(String message) {
		session.setAttribute("alertMessage", message);
	}
	
	public void youNeededLogin(String message) {
		this.setAlertMessage(message);
		this.goToPage("member/memberLoginSelectForm.jsp");
	}
	
}
