package com.FunFinder360.Controller.Member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberActivitesDao;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Controller.SuperClass;

public class MemberProfitController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		if (super.logInfo == null & super.loginfoOwner == null) {
			System.out.println("logInfo : " + logInfo);
			String massage = "로그인이 필요한 페이지입니다. ";
			super.setAlertMessage(massage);
			super.goToPage("member/memberLoginSelectForm.jsp");
		}

		try {
			MemberActivitesDao dao = new MemberActivitesDao();
			String personalUserId = null;
			String ownerUserId = null;

			if (super.logInfo != null) {
				personalUserId = super.logInfo.getUserId();
			} else if (super.loginfoOwner != null) {
				ownerUserId = super.loginfoOwner.getUserId();
			}

			if (personalUserId != null) {
				String userId = personalUserId;
				
				try {
					System.out.println("지금은 유저 아이디 사용중 : " + userId );
					
					int readHitTotalCount = dao.getReadHitTotalCount(userId);
					int reviewTotalCount = dao.getReviewTotalCount(userId);
					
					List<PersonalActivity> dateReadHitCount = dao.getDateReadHitCount(userId);
					
					request.setAttribute("readHitTotalCount", readHitTotalCount);
					request.setAttribute("reviewTotalCount", reviewTotalCount);
					request.setAttribute("dateReadHitCount", dateReadHitCount);
					
					super.goToPage("member/personalProfit.jsp");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (ownerUserId != null) {
				System.out.println("오류 확인 1");
				
				String ownerId = ownerUserId;
				
				try {
					System.out.println("지금은 기업 아이디 사용중 :  " + ownerId);
					
					int readHitTotalCount = dao.getOwnerReadHitTotalCount(ownerId);
					int reviewTotalCount = dao.getOwnerReviewTotalCount(ownerId);
					
					List<PersonalActivity> dateReadHitCount = dao.getOwnerDateReadHitCount(ownerId);
					
					request.setAttribute("readHitTotalCount", readHitTotalCount);
					request.setAttribute("reviewTotalCount", reviewTotalCount);
					request.setAttribute("dateReadHitCount", dateReadHitCount);
					
					super.goToPage("member/ownerProfit.jsp");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}

}
