package com.FunFinder360.Controller.ReviewController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ReviewDao;
import com.FunFinder360.Bean.Model.Review;
import com.FunFinder360.Controller.SuperClass;

public class ReviewListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		ReviewDao dao = new ReviewDao();
		
		Review review = new Review();
		try {
			review = dao.getReviewDataToActivityId(activityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
