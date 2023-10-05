package com.FunFinder360.Controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerDao;
import com.FunFinder360.Bean.Model.MemberOwner;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class OwnerMemberListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		
		if (keyword != null) {
			if (keyword.contains("-")) {
				keyword = keyword.substring(2);
				keyword = keyword.replace("-", "/");
			}
		}
		
		MemberOwnerDao dao = new MemberOwnerDao();
		
		try {
			int totalCount = dao.getTotalRecodeCount(mode, keyword);
			String url = super.getUrlInfomation("ownerMemberList");
			boolean isGrid = true;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			List<MemberOwner> lists = dao.getMemberOwnerList(pageInfo);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("datalist", lists);
			super.goToPage("admin/ownerMemberList.jsp");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
