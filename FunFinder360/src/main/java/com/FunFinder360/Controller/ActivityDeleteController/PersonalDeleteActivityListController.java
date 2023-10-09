package com.FunFinder360.Controller.ActivityDeleteController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Controller.SuperClass;

public class PersonalDeleteActivityListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		ActivitesDao dao = new ActivitesDao();
		
		try {
			dao.deleteActivityData(activityId);
			
			super.goToPage("member/memberActivity.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
