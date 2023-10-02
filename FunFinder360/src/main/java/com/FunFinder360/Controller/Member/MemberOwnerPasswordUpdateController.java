package com.FunFinder360.Controller.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerPasswordUpdateDao;
import com.FunFinder360.Controller.SuperClass;

public class MemberOwnerPasswordUpdateController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("member/memberOwnerFindPasswordForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		String name = (String) session.getAttribute("name");
		String email = (String) session.getAttribute("email");
		String id = (String) session.getAttribute("id");
		String newPassword = request.getParameter("password");

		boolean updated = false;
		System.out.println("여기서 걸리는 건가2  : " + updated);

		MemberOwnerPasswordUpdateDao dao = new MemberOwnerPasswordUpdateDao();
		try {
			updated = dao.ownerupdatePassword(name, email, id, newPassword);
			System.out.println("여기서 걸리는 건가  : " + updated);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (updated) {
			String message = "새 비밀번호 변경에 성공했습니다. ";
			super.setAlertMessage(message);
			super.goToPage("member/memberLoginSelectForm.jsp");
		} else {
			String message = " 새 비밀번호 변경에 실패하였습니다. ";
			super.setAlertMessage(message);
			super.goToPage("member/memberLoginSelectForm.jsp");
		}
	}

}
