package com.FunFinder360.Controller.ActivityController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Model.PersonalActivitesList;
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
		List<PersonalActivitesList> lists = null;
		int totalCount = 0;

		try {
			if ("readhit".equals(mode) || "postedDate".equals(mode)) {
				totalCount = dao.GetLookTotalAllRecordCount(mode);
			} else {
				totalCount = dao.GetTotalRecordCount(mode, keyword);
			}

			String url = super.getUrlInfomation("activitesList");
			boolean isGrid = true;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

			if ("readhit".equals(mode) || "postedDate".equals(mode)) {
				lists = dao.getActivites(pageInfo);
			} else {
				lists = dao.getSelectAll(pageInfo);
			}

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
