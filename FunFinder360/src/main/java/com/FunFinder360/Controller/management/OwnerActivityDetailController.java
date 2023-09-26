package com.FunFinder360.Controller.management;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Controller.SuperClass;

public class OwnerActivityDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));

		OwnerActivitesDao dao = new OwnerActivitesDao();

		List<OwnerActivity> lists = null;

		try {
			int totalRecodeCount = dao.getTotalRecordCount(null, null);
			lists = dao.getDateByActivityId(activityId, totalRecodeCount);

			request.setAttribute("activityData", lists);
			request.setAttribute("totalRecodeCount", totalRecodeCount);
			super.goToPage("management/ownerActivityDetailForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
