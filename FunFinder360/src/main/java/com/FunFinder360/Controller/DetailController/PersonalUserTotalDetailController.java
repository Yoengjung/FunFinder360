package com.FunFinder360.Controller.DetailController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Controller.SuperClass;

public class PersonalUserTotalDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		String userId = request.getParameter("userId");
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		MemberPersonalUserDao dao = new MemberPersonalUserDao();
		ActivitesDao activityDao = new ActivitesDao();
		
		try {
			MemberPersonalUser bean = dao.getMemberData(userId);
	
			
			request.setAttribute("userData", bean);
			
			super.goToPage("admin/personalUserTotalDetail.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
