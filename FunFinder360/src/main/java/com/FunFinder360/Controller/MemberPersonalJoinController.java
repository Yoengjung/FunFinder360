package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;

public class MemberPersonalJoinController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("/member/memberPersonalJoinForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		MemberPersonalUser bean = new MemberPersonalUser();

		String id = request.getParameter("id");

		MemberPersonalUserDao dao = new MemberPersonalUserDao();

		try {
			boolean status = dao.duplicationIdCheck(id);

			if (status) {
				bean.setUserId(request.getParameter("id"));
				bean.setPassword(request.getParameter("password"));
				bean.setUsername(request.getParameter("username"));
				bean.setBirth(request.getParameter("birthDate"));
				bean.setPhoneNumber(request.getParameter("phoneNumber"));
				bean.setEmail(request.getParameter("email"));
				bean.setBio(request.getParameter("introduction"));

				try {
					dao.insertJoinData(bean);
					session.removeAttribute("alertMessage");
					super.goToPage("member/memberPersonalLoginForm.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				String message = "이미 회원인 아이디입니다.";
				super.setAlertMessage(message);
				super.goToPage("member/memberPersonalJoinForm.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
