package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerIdFindDao;
import com.FunFinder360.Bean.Model.Member;

public class MemberOwnerFindIdController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("member/memberOwnerFindIdForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		System.out.println("이름 : " + name);
		System.out.println("이메일 : " + email);

		MemberOwnerIdFindDao dao = new MemberOwnerIdFindDao();
		Member ownerfindId = null;

		try {
			ownerfindId = dao.ownerfindId(name, email);
			System.out.println(" ownerfindId : " + ownerfindId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ownerfindId == null) {
			String message = "아이디 또는 이메일의 정보가 잘못 되었습니다.";
			super.setAlertMessage(message);
			
			super.goToPage("member/memberOwnerFindIdForm.jsp");
		} else {
			 request.setAttribute("foundId", ownerfindId);
			 request.setAttribute("name", name);
			super.goToPage("member/memberFindId.jsp");
		}
	}

}
