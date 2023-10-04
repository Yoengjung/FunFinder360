package com.FunFinder360.Controller.Member;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class MemberListController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
			
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		
		MemberPersonalUserDao dao = new MemberPersonalUserDao();
		
		try {
			int totalCount = dao.GetTotalRecordCount(); 
			String url = super.getUrlInfomation("personalMemberList") ;
			boolean isGrid = false ;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			List<MemberPersonalUser> lists = dao.getMemberPeronalList();			
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("datalist", lists);
			super.goToPage("member/memberList.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
