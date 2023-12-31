package com.FunFinder360.Controller.QuestionsController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.QuestionListDao;
import com.FunFinder360.Bean.Model.QuestionsList;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class QuestionsListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");

		QuestionListDao dao = new QuestionListDao();
		List<QuestionsList> lists = null;

		try {
			int totalCount = dao.getTotalRecordCount(mode, keyword);
			String url = super.getUrlInfomation("questionsList");
			boolean isGrid = false;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

			lists = dao.getSelectAll(pageInfo);

			request.setAttribute("questionsList", lists);
			request.setAttribute("pageInfo", pageInfo);
			super.goToPage("question/questionListForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
