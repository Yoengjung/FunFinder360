package com.FunFinder360.Controller.CommonQuestionsController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.CommonQuestionDao;
import com.FunFinder360.Bean.Model.CommonQuestion;
import com.FunFinder360.Controller.SuperClass;

public class CQDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int qeustion_id = Integer.parseInt(request.getParameter("qeustion_id"));
		
		CommonQuestionDao dao = new CommonQuestionDao();
		
		try {
			CommonQuestion bean = dao.getDataByQuestionId(qeustion_id);
			
			request.setAttribute("commonQuestionData", bean);
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
