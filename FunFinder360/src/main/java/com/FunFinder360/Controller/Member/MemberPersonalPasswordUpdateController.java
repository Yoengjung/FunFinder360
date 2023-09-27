package com.FunFinder360.Controller.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPasswordUpdateDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.FunFinder360.Controller.SuperClass;

public class MemberPersonalPasswordUpdateController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("/member/memberUpdatePassword.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)  {
		super.doPost(request, response);

		String name = (String) session.getAttribute("name");
		String email = (String) session.getAttribute("email");
		String id = (String) session.getAttribute("id");
		String newPassword = request.getParameter("password");

		boolean updated = false;

		MemberPasswordUpdateDao dao = new MemberPasswordUpdateDao();
		try {
			updated = dao.updatePassword(name, email, id, newPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("update" + updated);
		
		if (updated) {
			String message = "새 비밀번호 변경에 성공했습니다.";
			super.setAlertMessage(message);
			super.goToPage("member/memberLoginSelectForm.jsp");
		}else {
			String message = "새 비밀번호 변경에 실패하였습니다.";
			super.setAlertMessage(message);
			super.goToPage("member/memberLoginSelectForm.jsp");
		}

	}

}
