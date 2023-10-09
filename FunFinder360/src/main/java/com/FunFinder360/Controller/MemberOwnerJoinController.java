package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerUserDao;
import com.FunFinder360.Bean.Model.MemberOwner;

public class MemberOwnerJoinController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("/member/memberOwnerJoinForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		MemberOwner bean = new MemberOwner();

		String id = request.getParameter("id");

		MemberOwnerUserDao dao = new MemberOwnerUserDao();

		try {
			boolean status = dao.duplicationOwnerIdCheck(id);

			if (status) {
				bean.setUserId(request.getParameter("id"));
				bean.setPassword(request.getParameter("password"));
				bean.setUserName(request.getParameter("username"));
				bean.setBusinessName(request.getParameter("businessName"));
				bean.setBusinessType(request.getParameter("businessType"));
				bean.setBusinessNumber(Integer.parseInt(request.getParameter("businessNumber")));
				bean.setPhoneNumber(request.getParameter("phoneNumber"));
				bean.setEmail(request.getParameter("email"));
				bean.setBio(request.getParameter("introduction"));

				try {
					dao.insertOwnerJoinData(bean);
					session.removeAttribute("alertMessage");
					super.goToPage("member/memberOwnerLoginForm.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				String message = "이미 회원인 아이디입니다.";
				super.setAlertMessage(message);
				super.goToPage("member/memberOwnerJoinForm.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
