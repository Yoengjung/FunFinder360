package com.FunFinder360.Controller.ChangeController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Controller.SuperClass;
import com.FunFinder360.Controller.Member.MemberDetailController;

public class PersonalUserChangeEmailController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String userId = request.getParameter("userId");
		String newEmail = request.getParameter("newEmail");

		MemberPersonalUserDao dao = new MemberPersonalUserDao();

		try {
			int cnt = dao.changeEmail(userId, newEmail);

			if (cnt >= 0) {
				new MemberDetailController().doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
