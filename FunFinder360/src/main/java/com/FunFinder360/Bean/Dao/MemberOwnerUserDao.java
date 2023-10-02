package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.FunFinder360.Bean.Model.MemberOwner;

public class MemberOwnerUserDao extends SuperDao {
	// 업주 유저 아이디 중복 체크
	public boolean duplicationOwnerIdCheck(String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "select count(*) as cnt from owner_users where userid = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, id);

		rs = pstmt.executeQuery();

		int cnt = 1;
		if (rs.next()) {
			cnt = Integer.parseInt(rs.getString("cnt"));
		}
		boolean status = false;

		if (cnt == 0) {
			status = true;
		}

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			connection.close();
		}
		return status;
	}

	// 업주 정보 데이터베이스 저장
	public void insertOwnerJoinData(MemberOwner bean) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into owner_users(userid, password, username, BusinessName , BusinessType, BusinessNumber, phoneNumber, email, bio) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, bean.getUserId());
		pstmt.setString(2, bean.getPassword());
		pstmt.setString(3, bean.getUserName());
		pstmt.setString(4, bean.getBusinessName());
		pstmt.setString(5, bean.getBusinessType());
		pstmt.setInt(6, bean.getBusinessNumber());
		pstmt.setString(7, bean.getPhoneNumber());
		pstmt.setString(8, bean.getEmail());
		pstmt.setString(9, bean.getBio());

		pstmt.executeUpdate();

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			connection.close();
		}
	}

	public MemberOwner getMemberData(String userId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();
		
		String sql = "select * from owner_users where userid = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		rs = pstmt.executeQuery();
		
		MemberOwner member = new MemberOwner();
		
		if(rs.next()) {
			member = this.getBeanData(rs);
		}
		
		return member;
	}

	private MemberOwner getBeanData(ResultSet rs) throws Exception{
		MemberOwner member = new MemberOwner();
		
		member.setUserId(rs.getString("userId"));
		member.setPassword(rs.getString("password"));
		member.setUserName(rs.getString("username"));
		member.setBusinessName(rs.getString("businessName"));
		member.setBusinessType(rs.getString("businessType"));
		member.setBusinessNumber(rs.getInt("businessNumber"));
		member.setPhoneNumber(rs.getString("phoneNumber"));
		member.setEmail(rs.getString("email"));
		member.setBio(rs.getString("bio"));
		member.setRegistrationDate(rs.getString("registrationDate"));

		return member;
	}
	
	
	
	
}
