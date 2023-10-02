package com.FunFinder360.Bean.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.FunFinder360.Bean.Model.Member;

public class MemberIdFindDao extends SuperDao {

	public Member findId(String name, String email) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select userId from personal_users ";
		sql += " where username = ? and email = ? ";

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);
	

		pstmt.setString(1, name);
		pstmt.setString(2, email);
		
		

		rs = pstmt.executeQuery();
		
		System.out.println(sql);

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

		bean.setId(rs.getString("userId"));

		return bean;
	}

}
