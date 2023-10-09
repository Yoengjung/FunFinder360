package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.MemberOwner;
import com.FunFinder360.Bean.Model.OwnerActivity;

import Utility.Paging;

public class MemberOwnerDao extends SuperDao {

	public MemberOwner getDataByPk(String id, String password) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select * from Owner_Users";
		sql += " where userid = ? and password = ?";

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

		bean.setUserId(rs.getString("userid"));
		bean.setPassword(rs.getString("password"));
		bean.setUserName(rs.getString("username"));
		bean.setBusinessName(rs.getString("BusinessName"));
		bean.setBusinessType(rs.getString("BusinessType"));
		bean.setBusinessNumber(Integer.valueOf(rs.getInt("BusinessNumber")));
		bean.setPhoneNumber(rs.getString("PhoneNumber"));
		bean.setEmail(rs.getString("email"));
		bean.setRegistrationDate(String.valueOf(rs.getDate("registrationDate")));

		return bean;
	}

	public int getTotalRecodeCount(String mode, String keyword) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검새 키워드명 : " + keyword);

		String sql = " select count(*) as cnt from owner_users ";
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

	public List<MemberOwner> getMemberOwnerList(Paging pageInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = " select userid, password, username, businessname, businesstype, businessnumber, phonenumber, email, TO_CHAR(registrationdate, 'yyyy-mm-dd') AS registrationdate, bio ";
		sql += " from ( select userid, password, username, businessname, businesstype, businessnumber, phonenumber, email, registrationdate,bio, RANK() OVER (ORDER BY email ASC) AS ranking ";
		sql += " from owner_users ";
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

		List<MemberOwner> lists = new ArrayList<MemberOwner>();

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

	public MemberOwner getOwnerData(String userId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "select * from owner_users where userid = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, userId);

		rs = pstmt.executeQuery();

		MemberOwner member = new MemberOwner();

		if (rs.next()) {
			member = getBeanData(rs);
		}
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return member;
	}

	public int getReadHitTotalCount(String userId) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		ResultSet rs = null;
		
		String sql = "select sum(readhit) totalReadHit from owner_activites where userid =? ";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		rs = pstmt.executeQuery();
		
		int totalReviewCount = 0;
		if (rs.next()) {
			totalReviewCount = rs.getInt("totalReadHit");
		}
		
		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		
		return totalReviewCount;
	}

	public int getReviewTotalCount(String userId) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		ResultSet rs = null;
		
		
		String sql = "select count(*) totalReview from owner_activites join (select activityId from reviews) re on owner_activites.activityId = re.activityid where userid = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		rs = pstmt.executeQuery();
		
		int totalReviewCount = 0;
		if (rs.next()) {
			totalReviewCount = rs.getInt("totalReview");
		}
		
		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		
		return totalReviewCount;
	}
	
	public List<OwnerActivity> getDateReadHitCount(String userId) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		ResultSet rs = null;
		//현재 날짜
		LocalDate now = LocalDate.now();
		List<OwnerActivity> lists = new ArrayList<OwnerActivity>();
		
		for (int i = 7; i >= 1; i--) {
			LocalDate date = now.minusDays(i);
		    String dateStr = date.toString();
		    String sql = "select sum(readhit) readhit from owner_activites where userid = ? and posteddate <= to_date(?, 'yyyy-mm-dd')";
			
		    pstmt = conn.prepareStatement(sql);

			System.out.println("data : " + dateStr);

			pstmt.setString(1, userId);
			pstmt.setString(2, dateStr);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				OwnerActivity bean = new OwnerActivity();
				try {
					bean.setReadHit(Integer.parseInt(rs.getString("readhit")));
					lists.add(bean);
				} catch (Exception e) {
					bean.setReadHit(0);
					lists.add(bean);
				}
			}
		    
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;
	}

	public void deleteUser(String userId) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "delete from owner_users where userid = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		pstmt.executeUpdate();
		
		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		
	}

}
