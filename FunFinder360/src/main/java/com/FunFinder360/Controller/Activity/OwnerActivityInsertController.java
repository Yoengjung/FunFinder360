package com.FunFinder360.Controller.Activity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.OwnerActivitesDao;
import com.FunFinder360.Bean.Model.MemberOwner;
import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Controller.SuperClass;
import com.FunFinder360.Controller.ActivityController.OwnerActivityListController;
import com.oreilly.servlet.MultipartRequest;

public class OwnerActivityInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		if (super.logInfo == null && super.loginfoOwner == null) {
			super.youNeededLogin("활동 등록을 위해서는 로그인이 필요합니다.");
			return;
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

		MemberOwner loginfoOwner = super.loginfoOwner;
		
		String contentAndImageOrder = mr.getParameter("contentAndImageOrder");

		String userId = loginfoOwner.getUserId();

		int hour = Integer.parseInt(mr.getParameter("hour"));
		int minute = Integer.parseInt(mr.getParameter("minute"));
		int time = hour * 60 + minute;
		
		String price = mr.getParameter("price");
		price = price.replace(",", "");
		int intPrice = Integer.parseInt(price);
		

		OwnerActivity ownerActivity = new OwnerActivity();

		ownerActivity.setUserid(userId);
		ownerActivity.setActivitiyName(mr.getParameter("title"));
		ownerActivity.setCategory(mr.getParameter("category"));
		ownerActivity.setLocation(mr.getParameter("province") + " " + mr.getParameter("districtValue"));
		ownerActivity.setLocationDetail(mr.getParameter("detail-location"));
		ownerActivity.setDuration(time);
		ownerActivity.setPrice(intPrice);
		ownerActivity.setActivitiyNumber(Integer.parseInt(mr.getParameter("activityNumber")));
		ownerActivity.setOpenTime(mr.getParameter("openTime"));
		ownerActivity.setCloseTime(mr.getParameter("closeTime"));
		ownerActivity.setEvent(mr.getParameter("event"));

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
		OwnerActivitesDao dao = new OwnerActivitesDao();
		try {
			status = dao.insertOwnerActivityData(ownerActivity, contentList, imageList, contentAndImageOrder);

			if (status == -1) {
				super.setAlertMessage("활동 등록에 실패했습니다.");
				new OwnerActivityInsertController().doGet(request, response);
			} else {
				super.session.removeAttribute("alertMessage");
				new OwnerActivityListController().doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
