package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberIdFindDao;
import com.FunFinder360.Bean.Dao.MemberOwnerDao;
import com.FunFinder360.Bean.Model.Member;
import com.FunFinder360.Bean.Model.MemberOwner;

public class MemberFindIdController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("member/memberFindIdForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String name = request.getParameter("name");

		String email = request.getParameter("email");

		
		MemberIdFindDao dao = new MemberIdFindDao();
		Member findId = null;
		
		try {
			findId = dao.findId(name, email);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (findId == null) {
			String message = "아이디 또는 이메일의 정보가 잘못 되었습니다.";
			super.setAlertMessage(message);
			
			super.goToPage("member/memberFindIdForm.jsp");
		} else {
			 request.setAttribute("foundId", findId);
			 request.setAttribute("name", name);
			super.goToPage("member/memberFindId.jsp");
		}
	}
}
