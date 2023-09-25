package com.FunFinder360.Controller.Member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberActivitesDao;
import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Controller.SuperClass;

import Utility.Paging;

public class MemberActivityController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		if (super.logInfo == null && super.loginfoOwner == null) {
			System.out.println("logInfo : " + logInfo);
			super.setAlertMessage("로그인이 필요한 페이지입니다.");
			super.goToPage("member/memberLoginSelectForm.jsp");
		}
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");

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

				List<PersonalActivity> lists = null;
				String userId = super.logInfo.getUserId();

				try {
					System.out.println("유저 아이디 :" + userId);
					int totalCount = dao.getPersonalTotalRecordCount(mode, keyword, userId);
					String url = super.getUrlInfomation("memberActivity");
					boolean isGrid = true;
					Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

					lists = dao.getPeronalSelectAll(pageInfo, userId);

					request.setAttribute("personalActivityList", lists);
					request.setAttribute("pageInfo", pageInfo);
					super.goToPage("member/memberActivity.jsp");

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (ownerUserId != null) {
				System.out.println("오류 확인 1");

				List<OwnerActivity> lists = null;
				String ownerId = super.loginfoOwner.getUserId();

				try {

					System.out.println("기업 아이디 : " + ownerId);
					int totalCount = dao.getOwnerTotalRecordCount(mode, keyword, ownerId);
					String url = super.getUrlInfomation("memberActivity");
					boolean isgrid = true;
					Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isgrid);

					lists = dao.getOwnerSelectAll(pageInfo, ownerId);

					request.setAttribute("ownerActivityList", lists);
					request.setAttribute("pageInfo", pageInfo);
					super.goToPage("member/memberActivity2.jsp");

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
