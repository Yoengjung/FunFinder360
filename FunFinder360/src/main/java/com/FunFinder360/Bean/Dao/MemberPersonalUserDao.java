package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.FunFinder360.Bean.Model.MemberPersonalUser;

public class MemberPersonalUserDao extends SuperDao {

	public MemberPersonalUser pGetDataByPk(String id, String password) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select * from personal_users";
		sql += " where user_id = ? and password = ? ";

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, id);
		pstmt.setString(2, password);

		rs = pstmt.executeQuery();

		MemberPersonalUser bean = null;
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

	private MemberPersonalUser getBeanData(ResultSet rs) throws Exception {
		MemberPersonalUser bean = new MemberPersonalUser();

		bean.setUserId(rs.getString("user_id"));
		bean.setPassword(rs.getString("password"));
		bean.setUsername(rs.getString("username"));
		bean.setBirth(rs.getString("user_birth_date"));
		bean.setPhoneNumber(rs.getString("phoneNumber"));
		bean.setEmail(rs.getString("email"));
		bean.setRegistration_date(rs.getString("registration_date"));

		return bean;
	}

	public void insertJoinData(MemberPersonalUser bean) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "insert into personal_users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getUserId());
		pstmt.setString(2, bean.getPassword());
		pstmt.setString(3, bean.getUsername());
		pstmt.setString(4, bean.getBirth());
		pstmt.setString(5, bean.getPhoneNumber());
		pstmt.setString(6, bean.getEmail());
		pstmt.setString(7, bean.getBio());
		pstmt.setString(8, bean.getProfileImage());
		pstmt.setString(9, bean.getRegistration_date());
		
		pstmt.executeUpdate();

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			connection.close();
		}		
	}

}
