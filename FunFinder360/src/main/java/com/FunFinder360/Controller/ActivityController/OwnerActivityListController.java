package com.FunFinder360.Controller.ActivityController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Bean.Model.OwnerActivityAndImage;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class OwnerActivityListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");

		OwnerActivitesDao dao = new OwnerActivitesDao();

		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword);
			String url = super.getUrlInfomation("OwnerActivitesList");
			boolean isGrid = true;
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

			List<OwnerActivityAndImage> lists = dao.selectAll(pageInfo);

			request.setAttribute("activity", lists);
			request.setAttribute("pageInfo", pageInfo);

			super.goToPage("activity/ownerActivitesList.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
