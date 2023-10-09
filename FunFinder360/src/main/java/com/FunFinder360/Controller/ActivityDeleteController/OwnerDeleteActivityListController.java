package com.FunFinder360.Controller.ActivityDeleteController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Controller.SuperClass;

public class OwnerDeleteActivityListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		int activityId = Integer.parseInt(request.getParameter("activityId"));

		OwnerActivitesDao dao = new OwnerActivitesDao();

		try {
			dao.deleteActivityData(activityId);

			super.goToPage("member/memberActivity2.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
