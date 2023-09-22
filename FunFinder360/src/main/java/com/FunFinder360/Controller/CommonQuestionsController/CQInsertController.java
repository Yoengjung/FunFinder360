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
		
		if (super.logInfo == null && super.loginfoOwner == null) {
			super.setAlertMessage("로그인이 필요한 페이지입니다.");
			super.goToPage("member/memberLoginSelectForm.jsp");
		}
		
		super.goToPage("question/commonQuestionInsertForm.jsp");
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		CommonQuestion  bean = new CommonQuestion() ;
		
		bean.setUserId(request.getParameter("userId"));
		bean.setTitle(request.getParameter("title"));
		bean.setContent(request.getParameter("content"));

		CommonQuestionDao dao = new CommonQuestionDao() ;
		
		int cnt = -1 ;
		try {
			cnt = dao.InsertData(bean, super.logInfo.getUserId()) ; 
			
			if(cnt == -1) {
				super.goToPage("question/commonQuestionInsertFrom.jsp");
				
			}else {
				super.goToPage("/common/main.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
