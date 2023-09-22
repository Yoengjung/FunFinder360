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


		if (super.logInfo.getUserId() != null) {
			MemberActivitesDao dao = new MemberActivitesDao();
			List<PersonalActivity> lists = null;
			String personal_id = super.logInfo.getUserId();
			try {
				System.out.println("유저 아이디 :" +  personal_id);
				int totalCount = dao.getPersonalTotalRecordCount(mode, keyword, personal_id);
				String url = super.getUrlInfomation("personalActivityList");
				boolean isGrid = false;
				Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);

				lists = dao.getPseronalSelectAll(pageInfo, personal_id );

				request.setAttribute("personalActivityList", lists);
				request.setAttribute("pageInfo", pageInfo);
				super.goToPage("member/memberActivity.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (super.loginfoOwner.getUserId() != null) {
			MemberActivitesDao dao = new MemberActivitesDao();
			List<OwnerActivity> lists = null;
			String owner_id = super.loginfoOwner.getUserId();
			
			try {
				//int totalCount = dao.getOwnerTotalRecordCount(mode, keyword, owner_id);
				String url = super.getUrlInfomation("ownerActivityList");
				boolean isgrid = false;
				//Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isgrid);
				
				//lists = dao.getOwnerSelectAll(pageInfo);
				request.setAttribute("ownerActivityList", lists);
				//request.setAttribute("pageInfo", pageInfo);
				super.goToPage("member/memberActivity2.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			

		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

	}

}
