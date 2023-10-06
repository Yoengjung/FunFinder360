package com.FunFinder360.Controller.DetailController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class PersonalUserTotalDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		String userId = request.getParameter("userId");

		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");

		MemberPersonalUserDao dao = new MemberPersonalUserDao();
		ActivitesDao activityDao = new ActivitesDao();

		try {
			MemberPersonalUser bean = dao.getMemberData(userId);
			int totalCount = activityDao.GetTotalRecordCount(mode, keyword, userId); // 모든 활동을 가져온다.
			String url = super.getUrlInfomation("personalUserTotalDetail&userId=" + userId);
			boolean isGrid = false;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

			List<PersonalActivity> lists = activityDao.getPersonalUserToUserId(pageInfo, userId);
			
			int readHitTotalCount = dao.getReadHitTotalCount(userId);
			int reviewTotalCount = dao.getReviewTotalCount(userId);
			List<PersonalActivity> dateReadHitCount = dao.getDateReadHitCount(userId);

			request.setAttribute("activityData", lists);
			request.setAttribute("userData", bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("readHitTotalCount", readHitTotalCount);
			request.setAttribute("reviewTotalCount", reviewTotalCount);
			request.setAttribute("dateReadHitCount", dateReadHitCount);

			super.goToPage("admin/personalUserTotalDetail.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
