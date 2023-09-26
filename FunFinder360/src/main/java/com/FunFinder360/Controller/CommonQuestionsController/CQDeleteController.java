package com.FunFinder360.Controller.CommonQuestionsController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.CommonQuestionDao;

import com.FunFinder360.Controller.SuperClass;

public class CQDeleteController extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		super.doGet(request, response);
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		CommonQuestionDao dao = new CommonQuestionDao();
		int cnt = -1;

		try {
			cnt = dao.DeleteDate(questionId);
			new CQListController().doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
