package com.FunFinder360.Controller.OwnerActivitesListController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Bean.Model.OwnerActivitesList;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class OwnerTravelActivitesListListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");

		OwnerActivitesDao dao = new OwnerActivitesDao();
		List<OwnerActivitesList> lists = null;
		int totalCount = 0;

		try {
			if ("readhit".equals(mode) || "postedDate".equals(mode)) {
				totalCount = dao.GetOwnerTravelLookTotalRecordCount(mode);
			} else {
				totalCount = dao.GetOwnerTravelTotalRecordCount(mode, keyword);
			}

			String url = super.getUrlInfomation("ownerTravelActivitesList");
			boolean isGrid = true;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			if ("readhit".equals(mode) || "postedDate".equals(mode)) {
				lists = dao.getOwnerTravelActivites(pageInfo);
			} else {
				lists = dao.getOwnerTravelSelectAll(pageInfo);
			}
			request.setAttribute("ownerActivity", lists);
			request.setAttribute("pageInfo", pageInfo);

			super.goToPage("activity/ownerTravelActivitesListForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
