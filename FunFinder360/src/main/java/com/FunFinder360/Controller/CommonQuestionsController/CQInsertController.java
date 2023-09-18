package com.FunFinder360.Controller.CommonQuestionsController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.CommonQuestionDao;
import com.FunFinder360.Bean.Model.CommonQuestion;
import com.FunFinder360.Controller.SuperClass;

public class CQInsertController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		super.goToPage("question/commonQuestionInsertFrom.jsp");
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		CommonQuestion  bean = new CommonQuestion() ;
		
		bean.setQuestionId(super.getNumberData(request.getParameter("questionId")));
		bean.setUserId(request.getParameter("userId"));
		bean.setTitle(request.getParameter("title"));
		bean.setContent(request.getParameter("content"));
		bean.setPostedDate(request.getParameter("postedDate"));		
		bean.setReadhit(super.getNumberData(request.getParameter("readhit")));

		CommonQuestionDao dao = new CommonQuestionDao() ;
		int cnt = -1 ;
		try {
			cnt = dao.InsertData(bean) ; 
			
			if(cnt == -1) {
				super.goToPage("question/commonQuestionInsertFrom.jsp");
				
			}else {
//				new CQListController().doGet(request, response); 
				super.goToPage("/common/main.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
