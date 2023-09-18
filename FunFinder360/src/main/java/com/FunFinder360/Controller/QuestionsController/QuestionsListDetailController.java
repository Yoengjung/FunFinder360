package com.FunFinder360.Controller.QuestionsController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.QuestionListDao;
import com.FunFinder360.Bean.Model.QuestionsList;
import com.FunFinder360.Controller.SuperClass;

public class QuestionsListDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		int questionListId = Integer.parseInt(request.getParameter("questionListId"));

		QuestionListDao dao = new QuestionListDao();

		List<QuestionsList> lists = null;

		try {
			int totalRecodeCount = dao.getTotalRecordCount(null, null);
			lists = dao.getDateByQuestionId(questionListId, totalRecodeCount);

			request.setAttribute("questionData", lists);
			request.setAttribute("totalRecodeCount", totalRecodeCount);
			super.goToPage("question/questionDetailForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
