package com.FunFinder360.Controller.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.FunFinder360.Controller.SuperClass;

public class MemberDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		if(super.logInfo == null) {
			super.setAlertMessage("로그인을 해야 사용할 수 있는 페이지입니다.");
			super.goToPage("member/memberLoginSelectForm.jsp");
		}
		
		MemberPersonalUser member = new MemberPersonalUser();
		
		MemberPersonalUserDao dao = new MemberPersonalUserDao();
	
		try {
			member = dao.getMemberData(super.logInfo.getUserId());
			
			request.setAttribute("bean", member);
			super.goToPage("member/memberDetail.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
	}
}
