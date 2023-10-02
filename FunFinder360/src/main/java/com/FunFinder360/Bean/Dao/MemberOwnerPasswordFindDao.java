package com.FunFinder360.Bean.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.FunFinder360.Bean.Model.Member;

public class MemberOwnerPasswordFindDao extends SuperDao {

	public Member findpassword(String name, String email, String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select password from owner_users ";
		sql += " where username = ? and email = ? and userId = ? ";

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, name);
		pstmt.setString(2, email);
		pstmt.setString(3, id);

		rs = pstmt.executeQuery();

		Member bean = null;

		if (rs.next()) {
			bean = getBeanData(rs);
		}
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (connection != null) {
			connection.close();
		}
		return bean;
	}

	private Member getBeanData(ResultSet rs) throws Exception {
		Member bean = new Member();

		bean.setPassword(rs.getString("password"));
		System.out.println("기업입니다.getBeanDate :  " + bean);

		return bean;
	}

}
