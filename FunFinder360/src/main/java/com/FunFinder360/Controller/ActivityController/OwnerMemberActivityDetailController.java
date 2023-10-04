package com.FunFinder360.Controller.ActivityController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberActivitesDetailDao;
import com.FunFinder360.Bean.Model.OwnerActivityDetail;
import com.FunFinder360.Controller.SuperClass;

public class OwnerMemberActivityDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		MemberActivitesDetailDao dao = new MemberActivitesDetailDao();
		OwnerActivityDetail ownerActivityDetail = null;
		
		try {
			ownerActivityDetail = dao.getOwnerActivityDate(activityId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("ownerActivityData", ownerActivityDetail);
		super.goToPage("member/ownerMemberActivityDetail.jsp");
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}

}
