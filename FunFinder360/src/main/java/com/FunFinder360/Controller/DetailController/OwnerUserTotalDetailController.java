package com.FunFinder360.Controller.DetailController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerDao;
import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Bean.Model.MemberOwner;
import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class OwnerUserTotalDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		String userId = request.getParameter("userId");

		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");

		MemberOwnerDao dao = new MemberOwnerDao();
		OwnerActivitesDao activityDao = new OwnerActivitesDao();
		
		try {
			MemberOwner bean = dao.getOwnerData(userId);
			int totalCount = activityDao.GetOwnerTotalRecordCount(mode, keyword, userId);
			String url = super.getUrlInfomation("ownerUserTotalDetail&userId=" + userId);
			boolean isGrid = false;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			List<OwnerActivity> lists = activityDao.getOwnerUserToUserId(pageInfo, userId);
			int readHitTotalCount = dao.getReadHitTotalCount(userId);
			int reviewTotalCount = dao.getReviewTotalCount(userId);
			
			request.setAttribute("activityData", lists);
			request.setAttribute("userData", bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("readHitTotalCount", readHitTotalCount);
			request.setAttribute("reviewTotalCount", reviewTotalCount);
			
			super.goToPage("admin/ownerUserTotalDetail.jsp");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
