package com.FunFinder360.Controller.ActivityController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberActivitesDetailDao;
import com.FunFinder360.Controller.SuperClass;

public class OwnerMemberActivityDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		MemberActivitesDetailDao dao = new MemberActivitesDetailDao();
		
		
				
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

}
