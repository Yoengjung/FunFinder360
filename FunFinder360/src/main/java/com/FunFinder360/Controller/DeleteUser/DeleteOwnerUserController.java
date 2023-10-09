package com.FunFinder360.Controller.DeleteUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerDao;
import com.FunFinder360.Controller.SuperClass;

public class DeleteOwnerUserController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String userId = request.getParameter("userId");
		
		MemberOwnerDao dao = new MemberOwnerDao();
		
		try {
			dao.deleteUser(userId);
			
			session.invalidate();
			
			super.goToPage("/common/main.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
