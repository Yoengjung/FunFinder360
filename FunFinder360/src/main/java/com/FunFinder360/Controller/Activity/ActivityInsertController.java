package com.FunFinder360.Controller.Activity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Controller.SuperClass;
import com.oreilly.servlet.MultipartRequest;

public class ActivityInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		if(super.logInfo == null) {
			super.youNeededLogin("활동 등록을 위해서는 로그인이 필요합니다.");
			return ;
		}	

		super.goToPage("activity/activityInsertForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		MultipartRequest mr = (MultipartRequest) request.getAttribute("mr");
		
		MemberPersonalUser loginfo = super.logInfo;
		
		String userId = loginfo.getUserId();

		int hour = Integer.parseInt(mr.getParameter("hour"));
		int minute = Integer.parseInt(mr.getParameter("minute"));
		
		int time = hour * 60 + minute;
		
		PersonalActivity personalActivity = new PersonalActivity();
		
		personalActivity.setUserId(userId);
		personalActivity.setActivityName(mr.getParameter("title"));
		personalActivity.setCategory(mr.getParameter("category"));
		personalActivity.setLocation(mr.getParameter("province") + " " + mr.getParameter("district"));
		personalActivity.setLocationDetail(mr.getParameter("detail-location"));
		personalActivity.setDuration(time);
		personalActivity.setCost(Integer.parseInt(mr.getParameter("cost")));
		personalActivity.setActivityNumber(Integer.parseInt(mr.getParameter("activityNumber")));
		personalActivity.setRating(Integer.parseInt(mr.getParameter("rating")));
		

		int contantCount = Integer.parseInt(mr.getParameter("contentCount"));
		int imageCount = Integer.parseInt(mr.getParameter("imageCount"));
		
		List<String> contentList = new ArrayList<String>();
		List<String> imageList = new ArrayList<String>();
		
		for (int i = 0; i < contantCount; i++) {
			String contentItem = mr.getParameter("content" + i);
			System.out.println("contentItem : " + contentItem);
			contentList.add(contentItem);
		}
		for (int i = 0; i < imageCount; i++) {
			String imageItem = mr.getFilesystemName("image" + i);
			System.out.println("imageItem : " + imageItem);
			imageList.add(imageItem);
		}
		
		int status = -1;
		try {
			ActivitesDao Dao = new ActivitesDao();
			
			status = Dao.insertPersonalActivityData(personalActivity, contentList, imageList);
			
			if(status == -1) {
				super.setAlertMessage("활동 등록에 실패했습니다.");
				new ActivityInsertController().doGet(request, response);
			} else {
				super.goToPage("common/main.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
