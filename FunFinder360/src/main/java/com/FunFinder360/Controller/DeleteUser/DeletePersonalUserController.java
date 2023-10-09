package com.FunFinder360.Controller.DeleteUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Controller.SuperClass;

public class DeletePersonalUserController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String userId = request.getParameter("userId");
		
		MemberPersonalUserDao dao = new MemberPersonalUserDao();
		
		try {
			dao.deleteUser(userId);
			
			session.invalidate();
			
			super.goToPage("/common/main.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
