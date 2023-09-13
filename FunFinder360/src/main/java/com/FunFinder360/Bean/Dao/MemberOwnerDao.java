package com.FunFinder360.Bean.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.FunFinder360.Bean.Model.MemberOwner;

public class MemberOwnerDao extends SuperDao {

	public MemberOwner getDataByPk(String id, String password) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select * from Owner_Users";
		sql += " where user_id = ? and password = ?";

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, id);
		pstmt.setString(2, password);

		rs = pstmt.executeQuery();

		MemberOwner bean = null;
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

	private MemberOwner getBeanData(ResultSet rs) throws Exception {
		MemberOwner bean = new MemberOwner();

		bean.setUser_id(rs.getString("user_id"));
		bean.setPassword(rs.getString("password"));
		bean.setUsername(rs.getString("username"));
		bean.setBusiness_Name(rs.getString("Business_Name"));
		bean.setBusiness_Type(rs.getString("Business_Type"));
		bean.setBusiness_Number(Integer.valueOf(rs.getInt("Business_Number"))); // 확인하기
		bean.setPhoneNumber(Integer.valueOf(rs.getInt("PhoneNumber")));
		bean.setEmail(rs.getString("email"));
		bean.setRegistration_date(String.valueOf(rs.getDate("registration_date")));

		return bean;
	}

}
