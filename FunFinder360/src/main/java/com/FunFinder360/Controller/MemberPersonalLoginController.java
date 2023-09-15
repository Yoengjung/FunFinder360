package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;

public class MemberPersonalLoginController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		super.session.removeAttribute("alertMessage");
		
		super.goToPage("/member/memberPersonalLoginForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberPersonalUserDao dao = new MemberPersonalUserDao();
		MemberPersonalUser bean = null;
		
		try {
			bean = dao.GetPersonalUserDataByPk(id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (bean == null) {
			String message = "로그인 정보가 잘못 되었습니다.";
			
			super.setAlertMessage(message);
			
			super.goToPage("/member/memberPersonalLoginForm.jsp");
			
		} else {
			super.session.setAttribute("loginfo", bean);
			session.removeAttribute("alertMessage");
			new HomeController().doGet(request, response);
		}
		
		
	}
}
