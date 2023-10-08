package com.FunFinder360.Controller.PersonalActivitesListController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Model.PersonalActivitesList;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class CultureActivitesListController extends SuperClass {
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
<<<<<<< HEAD
				totalCount = dao.GetCultureLookTotalRecordCount(mode); // 전체 수를 계산 
				System.out.println("1. totalCount의 수 : " + totalCount);
=======
				totalCount = dao.GetCultureLookTotalRecordCount(mode);
				
>>>>>>> a22826cde19333d362ed56cd7f72f4820cce6506
			} else {
				totalCount = dao.GetCultureTotalRecordCount(mode, keyword);
				System.out.println("2. totalCount의 수 : " + totalCount);
			}

			String url = super.getUrlInfomation("cultureActivitesList");
			boolean isGrid = true;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			if ("readhit".equals(mode) || "postedDate".equals(mode)) {
				lists = dao.getCultureActivites(pageInfo);
			} else {
				lists = dao.getCultureSelectAll(pageInfo);
			}
			
			request.setAttribute("personalActivity", lists);
			request.setAttribute("pageInfo", pageInfo);
			
			super.goToPage("activity/personalCultureActivitesListForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}