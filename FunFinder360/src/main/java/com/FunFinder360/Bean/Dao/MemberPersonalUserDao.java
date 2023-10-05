package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.MemberPersonalUser;

import Utility.Paging;

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

	public MemberPersonalUser getMemberData(String userId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "select * from personal_users where userid = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, userId);

		rs = pstmt.executeQuery();

		MemberPersonalUser member = new MemberPersonalUser();

		if (rs.next()) {
			member = this.getBeanData(rs);
		}

		return member;
	}

	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색 키워드명 : " + keyword);

		String sql = " select count(*) as cnt from personal_users ";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

		rs = pstmt.executeQuery();

		int cnt = -1;

		if (rs.next()) {
			cnt = rs.getInt("cnt");
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

		return cnt;
	}

	public List<MemberPersonalUser> getMemberPeronalList(Paging pageInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = " select userid, password, username, birth, phonenumber, email, TO_CHAR(registrationdate, 'YYYY-MM-DD') AS registrationdate, bio"; 
		sql += " from (SELECT userid, password, username, birth, phonenumber, email, registrationdate, bio, RANK() OVER (ORDER BY email ASC) AS ranking ";
		sql += " from personal_users ";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";
		sql += " where ranking between ? AND ?";
		
		
		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);
		
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<MemberPersonalUser> lists = new ArrayList<MemberPersonalUser>();

		while (rs.next()) {
			lists.add(getBeanData(rs));
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

		return lists;
	}

	public int changePassword(String userId, String currentPassword, String newPassword) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		ResultSet rs = null;
		
		String sql = "select password from personal_users where userid = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		rs = pstmt.executeQuery();
		
		boolean passwordCheck = false;
		if (rs.next()) {
			if (rs.getString("password").equals(currentPassword)) {
				passwordCheck = true;
			}
		}
		int cnt = -1;
		if (passwordCheck) {
			sql = "update personal_users set password=? where userid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userId);
			
			cnt = pstmt.executeUpdate();	
		}
		return cnt;
	}

	public int changePhoneNumber(String userId, String newPhoneNumber) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "update personal_users set phoneNumber=? where userid = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, newPhoneNumber);
		pstmt.setString(2, userId);
		
		int cnt = -1;
		cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	public int changeEmail(String userId, String newEmail) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "update personal_users set email=? where userid = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, newEmail);
		pstmt.setString(2, userId);
		
		int cnt = -1;
		cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	public int changeBio(String userId, String newBio) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "update personal_users set bio=? where userid = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, newBio);
		pstmt.setString(2, userId);
		
		int cnt = -1;
		cnt = pstmt.executeUpdate();
		
		return cnt;
	}

}
