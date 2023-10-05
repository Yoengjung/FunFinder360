package com.FunFinder360.Controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class OwnerMemberActivityListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		if (keyword != null) {
			if (keyword.contains("-")) {
				keyword = keyword.substring(2);
				keyword = keyword.replace("-", "/");
			}
		}

		OwnerActivitesDao dao = new OwnerActivitesDao();
		
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword);
			String url = super.getUrlInfomation("ownerMemberActivityList");
			boolean isGrid = false;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

			List<OwnerActivity> lists = dao.getOwnerActivityList(pageInfo);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("dataList", lists);
			
			super.goToPage("admin/ownerMemberActivityList.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
