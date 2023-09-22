package com.FunFinder360.Controller.Activity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Model.PersonalActivityDetail;
import com.FunFinder360.Controller.SuperClass;

public class ActivityDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		ActivitesDao dao = new ActivitesDao();
		
		
		try {
			PersonalActivityDetail personalActivityDetail = dao.getPersonalActivityData(activityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.goToPage("activity/personalActivityDetailForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
