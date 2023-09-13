package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;

public class MemberPersonalLoginController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		super.goToPage("/member/memberPersonalLoginForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(id + " / " + password);
		
		MemberPersonalUserDao dao = new MemberPersonalUserDao();
		MemberPersonalUser bean = null;
		
		try {
			bean = dao.pGetDataByPk(id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (bean == null) {
			String message = "로그인 정보가 잘못 되었습니다.";
			
			request.setAttribute("errorMessage", message);
			System.out.println(message);
			
			super.goToPage("/member/memberPersonalLoginForm.jsp");
			
		} else {
			super.session.setAttribute("loginfo", bean);
			
			new HomeController().doGet(request, response);
		}
		
		
	}
}
