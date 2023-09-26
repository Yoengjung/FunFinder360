package com.FunFinder360.Controller.management;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Dao.QuestionListDao;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Bean.Model.QuestionsList;
import com.FunFinder360.Controller.SuperClass;

public class PersonalActivityDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		int activityId = Integer.parseInt(request.getParameter("activityId"));

		ActivitesDao dao = new ActivitesDao();

		List<PersonalActivity> lists = null;

		try {
			int totalRecodeCount = dao.getTotalRecordCount(null, null);
			lists = dao.getDateByActivityId(activityId, totalRecodeCount);

			request.setAttribute("activityData", lists);
			request.setAttribute("totalRecodeCount", totalRecodeCount);
			super.goToPage("management/personalActivityDetailForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
