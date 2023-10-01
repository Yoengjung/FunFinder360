package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPasswordFindDao;
import com.FunFinder360.Bean.Model.Member;

public class MemberFindPasswordController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("member/memberFindPasswordForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
				
		MemberPasswordFindDao dao = new MemberPasswordFindDao();
		Member findPassword = null;
		
		try {
			findPassword = dao.findpassword(name, email, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (findPassword == null) {
			//System.out.println(findPassword);
			System.out.println("아이디랑 이메일이 없다");
			String message = "아이디 또는 이메일의 정보가 잘못 되었습니다.";
			super.setAlertMessage(message);
			
			super.goToPage("member/memberFindPasswordForm.jsp");
		} else {
			System.out.println("아이디랑 이메일이 있다");
			request.setAttribute("name", name);
			
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("id", id);
			
			super.goToPage("member/memberUpdatePassword.jsp");
		}
		
	}
}
