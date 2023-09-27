package com.FunFinder360.Controller.ReviewController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ReviewDao;
import com.FunFinder360.Bean.Model.Review;
import com.FunFinder360.Controller.SuperClass;
import com.FunFinder360.Controller.Activity.ActivityDetailController;

public class ReviewInsertController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		Review review = new Review();
		review.setActivityId(Integer.parseInt(request.getParameter("activityId")));
		review.setUserId(request.getParameter("userName"));
		review.setRating(Integer.parseInt(request.getParameter("review-rating")));
		review.setReviewContent(request.getParameter("reviewContent"));
		
		ReviewDao dao = new ReviewDao();
		
		try {
			dao.insertReviewData(review);
			
			new ActivityDetailController().doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
