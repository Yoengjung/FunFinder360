package com.FunFinder360.Controller.ChangeController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerUserDao;
import com.FunFinder360.Controller.SuperClass;
import com.FunFinder360.Controller.Member.MemberDetailController;

public class OwnerUserChangePasswordController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String userId = request.getParameter("userId");
		String newPassword = request.getParameter("newPassword");
		
		MemberOwnerUserDao dao = new MemberOwnerUserDao();
		
		try {
			int cnt = dao.changePassword(userId, newPassword);
			
			if(cnt >= 0) {
				new MemberDetailController().doGet(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
