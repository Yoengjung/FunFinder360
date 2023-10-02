package com.FunFinder360.Controller.ReviewController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.FunFinder360.Bean.Dao.ReviewDao;
import com.FunFinder360.Bean.Model.Review;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class ReviewListController extends SuperClass {
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		int activityId = Integer.parseInt(request.getParameter("activityId"));

		System.out.println("activityId : " + activityId);

		ReviewDao dao = new ReviewDao();
		List<Review> reviews = null;

		try {
			int totalCount = dao.reviewToActivityIdCount(activityId);
			String url = super.getUrlInfomation("activityId");
			boolean isGrid = false;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

			reviews = dao.setSelectAll(pageInfo, activityId );
			
			
			System.out.println("reviews : " + reviews.toString());
			JSONArray jsArr = new JSONArray();

			for (Review review : reviews) {
				JSONObject obj = new JSONObject();

				obj.put("activityId", review.getActivityId());
				obj.put("userId", review.getUserId());
				obj.put("rating", review.getRating());
				obj.put("reviewContent", review.getReviewContent());
				obj.put("reviewOrder", review.getReviewOrder());
				obj.put("postedDate", review.getPostedDate());

				jsArr.add(obj);
			}
			request.setAttribute("jsArr", jsArr);
			request.setAttribute("pageInfo", pageInfo);

			super.goToPage("activity/personalActivityDetailForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
