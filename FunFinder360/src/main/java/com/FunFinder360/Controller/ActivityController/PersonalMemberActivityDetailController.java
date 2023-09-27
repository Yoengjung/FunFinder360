package com.FunFinder360.Controller.ActivityController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberActivitesDetailDao;
import com.FunFinder360.Bean.Model.PersonalActivityDetail;
import com.FunFinder360.Controller.SuperClass;

public class PersonalMemberActivityDetailController extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		int activityId = Integer.parseInt(request.getParameter("activityId"));

		MemberActivitesDetailDao dao = new MemberActivitesDetailDao();

		PersonalActivityDetail personalActivityDetail = null;

		try {
			personalActivityDetail = dao.getPersonalActivityData(activityId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("personalActivityData", personalActivityDetail);
		super.goToPage("member/personalMemberActivityDetail.jsp");

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

	}
}
