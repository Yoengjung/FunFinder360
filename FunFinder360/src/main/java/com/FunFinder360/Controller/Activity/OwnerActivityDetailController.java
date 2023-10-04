package com.FunFinder360.Controller.Activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Bean.Dao.ReviewDao;
import com.FunFinder360.Bean.Model.OwnerActivityDetail;
import com.FunFinder360.Bean.Model.Review;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class OwnerActivityDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		OwnerActivitesDao dao = new OwnerActivitesDao();
		ReviewDao reviewDao = new ReviewDao();
		OwnerActivityDetail ownerActivityDetail = null;
		
		List<Review> reviews = null;
		
		try {
			int totalCount = reviewDao.getTotalRecordCount(activityId);
			boolean isGrid = false;
			String url = super.getUrlInfomation("OwnerActivityDetail&activityId=" + activityId);
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, isGrid);
			
			reviews = reviewDao.getSelectAll(pageInfo, activityId);
			
			ownerActivityDetail = dao.getOwnerActivityData(activityId);
			//reviews = reviewDao.getReviewDataToActivityId(activityId);
			
			request.setAttribute("pageInfo", pageInfo);
			
			request.setAttribute("ownerActivityData", ownerActivityDetail);
			request.setAttribute("reviewData", reviews);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		super.goToPage("activity/ownerActivityDetailForm.jsp");
	}
}
