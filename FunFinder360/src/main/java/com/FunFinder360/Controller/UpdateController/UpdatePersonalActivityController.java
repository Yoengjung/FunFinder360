package com.FunFinder360.Controller.UpdateController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Controller.SuperClass;

public class UpdatePersonalActivityController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		int activityId = Integer.parseInt(request.getParameter("activityId"));
		
		ActivitesDao dao = new ActivitesDao();
		
		try {
			PersonalActivity activity = dao.get
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
