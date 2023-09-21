package com.FunFinder360.Controller.CommonQuestionsController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.CommonQuestionDao;
import com.FunFinder360.Bean.Model.CommonQuestion;
import com.FunFinder360.Controller.SuperClass;

public class CQDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		int questionId = Integer.parseInt(request.getParameter("questionId"));

		CommonQuestionDao dao = new CommonQuestionDao();

		List<CommonQuestion> lists = null;

		try {
			int totalRecodeCount = dao.getTotalRecordCount(null, null);
			lists = dao.getDataByQuestionId(questionId, totalRecodeCount);

			request.setAttribute("commonQuestionData", lists);
			request.setAttribute("totalRecodeCount", totalRecodeCount);
			super.goToPage("question/commonQuestionDetailForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}

}
