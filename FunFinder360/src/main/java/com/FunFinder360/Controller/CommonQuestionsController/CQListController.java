package com.FunFinder360.Controller.CommonQuestionsController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Model.CommonQuestion;
import com.FunFinder360.Controller.SuperClass;
import com.FunFinder360.Controller.QuestionsController.CommonQuestionDao;

import Utility.Paging;

public class CQListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		
		CommonQuestionDao dao = new CommonQuestionDao();
		
		List<CommonQuestion> lists = null;
		
		try {
			int totalCount = dao.getTotalRecordCount(mode, keyword);
			String url = super.getUrlInfomation("commonQuestionsList");
			boolean isGrid = false;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			lists = dao.getSelectAll(pageInfo);
			
			request.setAttribute("commonQuestionData", lists);
			request.setAttribute("pageInfo", pageInfo);
			super.goToPage("question/commonQuestionFo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
