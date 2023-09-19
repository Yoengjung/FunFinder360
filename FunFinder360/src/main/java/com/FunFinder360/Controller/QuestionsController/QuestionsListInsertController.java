package com.FunFinder360.Controller.QuestionsController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.QuestionListDao;
import com.FunFinder360.Bean.Model.QuestionsList;
import com.FunFinder360.Controller.SuperClass;

public class QuestionsListInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		super.goToPage("/question/questionListInsertFrom.jsp");
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		QuestionsList  bean = new QuestionsList() ;
		
		bean.setQuestionListId(super.getNumberData(request.getParameter("questionListId")));
		bean.setPersonalUserId(request.getParameter("personalUserId"));
		bean.setOwnerUserId(request.getParameter("ownerUserId"));
		bean.setTitle(request.getParameter("title"));
		bean.setContent(request.getParameter("content"));		
		bean.setReadhit(super.getNumberData(request.getParameter("readhit")));
		bean.setPostedDate(request.getParameter("postedDate"));

		QuestionListDao dao = new QuestionListDao() ;
		
		int cnt = -1 ;
		int check = 1;	// 개인, 업주 체크
		try {
			if(super.logInfo.getUserId()!=null) {
				// 개인
				cnt = dao.InsertData(bean, super.logInfo.getUserId(), check) ; 
			}else {
				check = 0;	// 업주
				cnt = dao.InsertData(bean, super.logInfo.getUserId(), check) ; 
			}

			if(cnt == -1) {
				super.goToPage("/question/questionListInsertFrom.jsp");
				
			}else {
				super.goToPage("/common/main.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
