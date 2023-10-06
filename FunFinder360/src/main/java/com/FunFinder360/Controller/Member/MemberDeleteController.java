package com.FunFinder360.Controller.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Bean.Dao.MemberOwnerUserDao;
import com.FunFinder360.Bean.Dao.MemberPersonalUserDao;
import com.FunFinder360.Controller.SuperClass;

public class MemberDeleteController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);

		int cnt = -1 ; 
		String id = request.getParameter("userId") ;
		if (super.logInfo == null && super.loginfoOwner == null) {
			super.setAlertMessage("로그인을 해야 사용할 수 있는 페이지입니다.");
			super.goToPage("member/memberLoginSelectForm.jsp");
		}

		if (super.logInfo != null) {
			
			MemberPersonalUserDao dao = new MemberPersonalUserDao();

			try {
				cnt = dao.deleteData(id) ;

				super.session.invalidate();
				super.goToPage("member/memberJoinSelectForm.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (super.loginfoOwner != null) {
			
			MemberOwnerUserDao dao = new MemberOwnerUserDao();

			try {
				cnt = dao.deleteData(id) ;
				
				super.session.invalidate();
				super.goToPage("member/memberJoinSelectForm.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
