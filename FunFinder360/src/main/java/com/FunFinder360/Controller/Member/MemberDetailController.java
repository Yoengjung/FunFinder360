package com.FunFinder360.Controller.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerUserDao;
import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.MemberOwner;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.FunFinder360.Controller.SuperClass;

public class MemberDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		if (super.logInfo == null && super.loginfoOwner == null) {
			super.setAlertMessage("로그인을 해야 사용할 수 있는 페이지입니다.");
			super.goToPage("member/memberLoginSelectForm.jsp");
		}

		if (super.logInfo != null) {
			MemberPersonalUser member = new MemberPersonalUser();
			MemberPersonalUserDao dao = new MemberPersonalUserDao();

			try {
				member = dao.getMemberData(super.logInfo.getUserId());

				request.setAttribute("bean", member);
				super.goToPage("member/memberPersonalDetail.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (super.loginfoOwner != null) {
			MemberOwner member = new MemberOwner();
			MemberOwnerUserDao dao = new MemberOwnerUserDao();

			try {
				member = dao.getMemberData(super.loginfoOwner.getUserId());
				request.setAttribute("bean", member);
				super.goToPage("member/memberOwnerDetail.jsp");
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
