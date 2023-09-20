package com.FunFinder360.Controller.QuestionsController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.QuestionListDao;
import com.FunFinder360.Bean.Model.QuestionsList;
import com.FunFinder360.Controller.SuperClass;

public class QuestionsInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		if (super.logInfo == null && super.loginfoOwner == null) {
			super.setAlertMessage("로그인을 해야 사용할 수 있는 페이지입니다.");
			super.goToPage("member/memberLoginSelectForm.jsp");
		}

		super.goToPage("/question/questionInsertForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		QuestionsList bean = new QuestionsList();

		bean.setQuestionListId(super.getNumberData(request.getParameter("questionListId")));
		bean.setPersonalUserId(request.getParameter("personalUserId"));
		bean.setOwnerUserId(request.getParameter("ownerUserId"));
		bean.setTitle(request.getParameter("title"));
		bean.setContent(request.getParameter("content"));
		bean.setReadhit(super.getNumberData(request.getParameter("readhit")));
		bean.setPostedDate(request.getParameter("postedDate"));

		QuestionListDao dao = new QuestionListDao();

		int cnt = -1;
		try {
			if (super.logInfo.getUserId() != null) {
				cnt = dao.InsertData(bean, super.logInfo.getUserId());
			} else if (super.loginfoOwner.getUserId() != null) {
				cnt = dao.InsertData(bean, super.loginfoOwner.getUserId());
			}

			if (cnt == -1) {
				super.goToPage("/question/questionInsertForm.jsp");

			} else {
				super.goToPage("/common/main.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
