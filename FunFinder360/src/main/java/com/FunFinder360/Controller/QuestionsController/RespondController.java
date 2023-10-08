package com.FunFinder360.Controller.QuestionsController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.QuestionListDao;
import com.FunFinder360.Bean.Model.QuestionsList;
import com.FunFinder360.Controller.SuperClass;

public class RespondController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		int questionListId = Integer.parseInt(request.getParameter("questionListId"));
		String respond = request.getParameter("respond");
		
		QuestionsList questionRespond = new QuestionsList();
		questionRespond.setQuestionListId(questionListId);
		questionRespond.setRespond(respond);
		
		QuestionListDao dao = new QuestionListDao();
		
		try {
			dao.insertRespond(questionRespond);
			new QuestionsListDetailController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
