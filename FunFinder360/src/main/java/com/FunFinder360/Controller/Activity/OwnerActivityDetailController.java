package com.FunFinder360.Controller.Activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Bean.Dao.ReviewDao;
import com.FunFinder360.Bean.Model.OwnerActivityDetail;
import com.FunFinder360.Bean.Model.Review;
import com.FunFinder360.Controller.SuperClass;

public class OwnerActivityDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		OwnerActivitesDao dao = new OwnerActivitesDao();
		ReviewDao reviewDao = new ReviewDao();
		OwnerActivityDetail ownerActivityDetail = null;
		
		List<Review> reviews = null;
		
		try {
			ownerActivityDetail = dao.getOwnerActivityData(activityId);
			reviews = reviewDao.getReviewDataToActivityId(activityId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("ownerActivityData", ownerActivityDetail);
		request.setAttribute("reviewData", reviews);
		super.goToPage("activity/ownerActivityDetailForm.jsp");
	}
}
