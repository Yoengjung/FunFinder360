package com.FunFinder360.Controller.Activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.ActivitesDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Controller.SuperClass;
import com.FunFinder360.Controller.ActivityController.ActivityListController;
import com.oreilly.servlet.MultipartRequest;

public class ActivityInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		if(super.logInfo == null && super.loginfoOwner == null) {
			super.youNeededLogin("활동 등록을 위해서는 로그인이 필요합니다.");
			return ;
		}	
		
		if (super.logInfo != null) {
			super.goToPage("activity/activityInsertForm.jsp");
		} else if (super.loginfoOwner != null) {
			super.goToPage("activity/ownerActivityInsertForm.jsp");
		}
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		MultipartRequest mr = (MultipartRequest) request.getAttribute("mr");
		
		MemberPersonalUser loginfo = super.logInfo;
		
		String contentAndImageOrder = mr.getParameter("contentAndImageOrder");
		
		String userId = loginfo.getUserId();

		int hour = Integer.parseInt(mr.getParameter("hour"));
		int minute = Integer.parseInt(mr.getParameter("minute"));
		int time = hour * 60 + minute;
		
		String cost = mr.getParameter("cost");
		cost = cost.replace(",", "");
		int intCost = Integer.parseInt(cost);
		
		PersonalActivity personalActivity = new PersonalActivity();

		personalActivity.setUserId(userId);
		personalActivity.setActivityName(mr.getParameter("title"));
		personalActivity.setCategory(mr.getParameter("category"));
		personalActivity.setLocation(mr.getParameter("province") + " " + mr.getParameter("districtValue"));
		personalActivity.setLocationDetail(mr.getParameter("detail-location"));
		personalActivity.setDuration(time);
		personalActivity.setCost(intCost);
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
			if (imageItem != null) {
		        String encodedImageItem;
				try {
					encodedImageItem = URLEncoder.encode(imageItem, "UTF-8");
					imageList.add(encodedImageItem);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		    }
		}
		
		int status = -1;
		ActivitesDao Dao = new ActivitesDao();
		try {
			status = Dao.insertPersonalActivityData(personalActivity, contentList, imageList, contentAndImageOrder);
			
			if(status == -1) {
				super.setAlertMessage("활동 등록에 실패했습니다.");
				new ActivityInsertController().doGet(request, response);
			} else {
				super.session.removeAttribute("alertMessage");
				new ActivityListController().doGet(request, response);
//				super.goToPage("activity/activitesList.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
