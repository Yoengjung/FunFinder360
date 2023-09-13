package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Bean.Model.Member;
import com.FunFinder360.Bean.Model.MemberPersonalUser;
import com.oreilly.servlet.MultipartRequest;

public class MemberPersonalJoinController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage("/member/memberPersonalJoinForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		MultipartRequest mr = (MultipartRequest) request.getAttribute("multpart");

		MemberPersonalUser bean = new MemberPersonalUser();

		bean.setUserId(mr.getParameter("id"));
		bean.setPassword(mr.getParameter("password"));
		bean.setUsername(mr.getParameter("username"));
		bean.setBirth(mr.getParameter("birthDate"));
		bean.setPhoneNumber(mr.getParameter("phoneNumber"));
		bean.setEmail(mr.getParameter("email"));
		bean.setBio(mr.getParameter("introduction"));
		bean.setProfileImage(mr.getFilesystemName("profileImage"));

		MemberPersonalUserDao dao = new MemberPersonalUserDao();

		try {
			dao.insertJoinData(bean);
			
			super.goToPage("member/memberPersonalLoginForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
