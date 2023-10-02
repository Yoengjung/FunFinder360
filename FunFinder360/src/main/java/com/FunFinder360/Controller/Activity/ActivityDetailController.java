package com.FunFinder360.Controller.Activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Dao.ReviewDao;
import com.FunFinder360.Bean.Model.PersonalActivityDetail;
import com.FunFinder360.Bean.Model.Review;
import com.FunFinder360.Controller.SuperClass;

public class ActivityDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		ActivitesDao dao = new ActivitesDao();
		ReviewDao reviewDao = new ReviewDao();
		PersonalActivityDetail personalActivityDetail = null;
		List<Review> reviews = null;
		try {
			personalActivityDetail = dao.getPersonalActivityData(activityId);
			reviews = reviewDao.getReviewDataToActivityId(activityId);
			System.out.println("reviews : " +reviews.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("personalActivityData", personalActivityDetail);
		request.setAttribute("reviewData", reviews);
		super.goToPage("activity/personalActivityDetailForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
