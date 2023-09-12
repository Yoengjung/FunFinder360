package com.FunFinder360.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Model.Member;
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

		MultipartRequest multipart = (MultipartRequest) request.getAttribute("multpart");

		String id = multipart.getParameter("id");
		String password = multipart.getParameter("password");
		String email = multipart.getParameter("email");
		String introduction = multipart.getParameter("introduction");
		String profileImage = multipart.getFilesystemName("profileImage");

		System.out.println("id : " + id);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		System.out.println("introduction : " + introduction);
		System.out.println("profile : " + profileImage);

		Member member = new Member();

		member.setId(id);
		member.setPassword(password);
		member.setEmail(email);
		member.setIntroduction(introduction);
		member.setProfileImage(profileImage);
	}
}
