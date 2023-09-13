package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerDao;
import com.FunFinder360.Bean.Model.MemberOwner;

public class MemberOwnerLoginController extends SuperClass {
	private final String PREFIX = "member/" ;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		super.goToPage(PREFIX + "memberOwnerLoginForm.jsp");
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(id + "/" + password);
		
		MemberOwnerDao dao = new MemberOwnerDao();
		MemberOwner bean = null;

		
		try {
			bean = dao.getDataByPk(id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean == null) { 
			String message = "로그인 정보가 잘못 되었습니다." ;
			super.setAlertMessage(message);
			
			super.goToPage(PREFIX + "memberOwnerLoginForm.jsp");

		}else { // 로그인 성공
			// session 영역에 나의 로그인 정보를 저장합니다.
			super.session.setAttribute("loginfo", bean);
			
			// 홈 화면으로 이동합니다. 차후 상품 목록 페이지로 갈 예정
			new HomeController().doGet(request, response);
		}
	}
}
