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

		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		ActivitesDao dao = new ActivitesDao();
		ReviewDao reviewDao = new ReviewDao(); // 페지징
		PersonalActivityDetail personalActivityDetail = null;
		
		List<Review> reviews = null;// 페이징
		
		try {
			int totalCount = reviewDao.getTotalRecordCount(activityId);
			boolean isGrid = false;
			String url = super.getUrlInfomation("activityDetail&activityId=" + activityId);
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, isGrid);
			
			reviews = reviewDao.getSelectAll(pageInfo, activityId);
			
			personalActivityDetail = dao.getPersonalActivityData(activityId);
			
			//reviews = reviewDao.getReviewDataToActivityId(activityId);
			
			
			request.setAttribute("pageInfo", pageInfo);
			
			request.setAttribute("personalActivityData", personalActivityDetail);
			request.setAttribute("reviewData", reviews);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
		super.goToPage("activity/personalActivityDetailForm.jsp");
	}

	
}
