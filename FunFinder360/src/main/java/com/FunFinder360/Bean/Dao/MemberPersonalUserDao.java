package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.FunFinder360.Bean.Model.MemberOwner;
import com.FunFinder360.Bean.Model.MemberPersonalUser;

public class MemberPersonalUserDao extends SuperDao {

	// 회원인지 확인하는 메소드
	public MemberPersonalUser GetPersonalUserDataByPk(String id, String password) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select * from personal_users";
		sql += " where userId = ? and password = ? ";

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

	// 데이터베이스에서 데이터를 가져와 memberPersonalUser객체에 저장
	private MemberPersonalUser getBeanData(ResultSet rs) throws Exception {
		MemberPersonalUser bean = new MemberPersonalUser();

		bean.setUserId(rs.getString("userId"));
		bean.setPassword(rs.getString("password"));
		bean.setUsername(rs.getString("username"));
		bean.setBirth(rs.getString("birth"));
		bean.setPhoneNumber(rs.getString("phoneNumber"));
		bean.setEmail(rs.getString("email"));
		bean.setBio(rs.getString("bio"));
		bean.setRegistration_date(rs.getString("registrationDate"));

		return bean;
	}

	// 회원 정보 데이터베이스 저장
	public void insertJoinData(MemberPersonalUser bean) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into personal_users(userid, password, username, birth, phoneNumber, email, bio) values(?, ?, ?, ?, ?, ?, ?)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, bean.getUserId());
		pstmt.setString(2, bean.getPassword());
		pstmt.setString(3, bean.getUsername());
		pstmt.setString(4, bean.getBirth());
		pstmt.setString(5, bean.getPhoneNumber());
		pstmt.setString(6, bean.getEmail());
		pstmt.setString(7, bean.getBio());

		pstmt.executeUpdate();

		conn.commit();

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			connection.close();
		}
	}
	// 업주 정보 데이터베이스 저장
	public void insertOwnerJoinData(MemberOwner bean) throws Exception{
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

		conn.commit();
		

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			connection.close();
		}
	}

	// 개인 유저 아이디 중복 체크
	public boolean duplicationIdCheck(String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "select count(*) as cnt from personal_users where userid = ?";

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

	public MemberPersonalUser getMemberData(String userId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();
		
		String sql = "select * from personal_users where userid = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		rs = pstmt.executeQuery();
		
		MemberPersonalUser member = new MemberPersonalUser();
		
		if(rs.next()) {
			member = this.getBeanData(rs);
		}
		
		return member;
	}

}
