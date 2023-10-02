package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerPasswordFindDao;
import com.FunFinder360.Bean.Model.Member;

public class MemberOwnerFindPasswordController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("member/memberOwnerFindPasswordForm.jsp");

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = request.getParameter("id");

		MemberOwnerPasswordFindDao dao = new MemberOwnerPasswordFindDao();
		Member findPassword = null;

		try {
			findPassword = dao.findpassword(name, email, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (findPassword == null) {
			String message = "입력한 정보가 일치하지 않습니다.";
			super.setAlertMessage(message);

			super.goToPage("member/memberOwnerFindPasswordForm.jsp");

		} else {
			request.setAttribute("name", name);
			
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("id", id);
			
			super.goToPage("member/memberOwnerUpdatePassword.jsp");
		}
	}

}
