package com.FunFinder360.Controller.ActivityController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Model.ActivityAndImage;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class ActivityListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");

		ActivitesDao dao = new ActivitesDao();

		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword);
			String url = super.getUrlInfomation("activitesList");
			boolean isGrid = true;

			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

			List<ActivityAndImage> lists = dao.selectAll(pageInfo);

			request.setAttribute("personalActivity", lists);
			request.setAttribute("pageInfo", pageInfo);

			super.goToPage("activity/activitesList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
