package com.FunFinder360.Controller.Activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Dao.ReviewDao;
import com.FunFinder360.Bean.Model.PersonalActivityDetail;
import com.FunFinder360.Bean.Model.Review;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class ActivityDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		System.out.println("activityId : " + activityId);

		ReviewDao reviewDao = new ReviewDao();

		ActivitesDao dao = new ActivitesDao();
		// ReviewDao reviewDao = new ReviewDao();
		PersonalActivityDetail personalActivityDetail = null;
		// List<Review> reviews = null;
		try {
			personalActivityDetail = dao.getPersonalActivityData(activityId);
			// reviews = reviewDao.getReviewDataToActivityId(activityId);
			// System.out.println("reviews : " +reviews.toString());

			int totalCount = reviewDao.reviewToActivityIdCount(activityId);
			String url = super.getUrlInfomation("activityId");
			boolean isGrid = true;

			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid, activityId);

			List<Review> lists = reviewDao.selectAll(pageInfo, activityId);
			request.setAttribute("reviewData", lists);
			request.setAttribute("pageInfo", pageInfo);

			request.setAttribute("personalActivityData", personalActivityDetail);
			// request.setAttribute("reviewData", reviews);
			super.goToPage("activity/personalActivityDetailForm.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
