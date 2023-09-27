package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Bean.Model.PersonalActivity;

import Utility.Paging;

public class MemberActivitesDao extends SuperDao {
	private OwnerActivity getOwnerBeanData(ResultSet rs) throws Exception {
		OwnerActivity bean = new OwnerActivity();
		
		bean.setActivityId(rs.getInt("activityId"));
		bean.setUserid(rs.getString("userId"));
		bean.setActivitiyName(rs.getString("activityName"));
		bean.setCategory(rs.getString("category"));
		bean.setLocation(rs.getString("location"));
		bean.setLocationDetail(rs.getString("locationDetail"));
		bean.setReadHit(rs.getInt("readHit"));
		bean.setEvent(rs.getString("event"));
		bean.setPostedDate(rs.getString("postedDate"));

		return bean;
	}

	
	public List<OwnerActivity> getOwnerSelectAll(Paging pageInfo, String ownerId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = " select activityId, userid, activityname, category, location, locationDetail, readhit, event, postedDate, ranking ";
		sql += " from (select activityId, userid, activityname, category, location, locationDetail, readhit, event, postedDate, rank() over(order by postedDate asc) as ranking ";
		sql += " from owner_activites where userid = ?";

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";

		sql += " where ranking between ? and ? ";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ownerId);
		pstmt.setInt(2, pageInfo.getBeginRow());
		pstmt.setInt(3, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<OwnerActivity> lists = new ArrayList<OwnerActivity>();

		while (rs.next()) {
			lists.add(getOwnerBeanData(rs));
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
	
	
	public int getOwnerTotalRecordCount(String mode, String keyword, String ownerId) throws Exception {
		System.out.println("필드명 : " + mode);
		System.out.println("키워드 : " + keyword);

		String sql = " select count(*) as cnt from owner_activites";
		sql += " where userId = ?";

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ownerId);

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
		if (conn != null) {
			conn.close();
		}

		return cnt;

	}

	public List<PersonalActivity> getPeronalSelectAll(Paging pageInfo, String userId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = " select activityId, userid, activityname, category, location, locationDetail, readhit, postedDate, ranking ";
		sql += " from (select activityId, userid, activityname, category, location, locationDetail, readhit, postedDate, rank() over(order by postedDate asc) as ranking ";
		sql += " from personal_activites where userid = ? " ;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
		}
		
		sql += " ) ";
		sql += " where ranking between ? and ? ";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setInt(2, pageInfo.getBeginRow());
		pstmt.setInt(3, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<PersonalActivity> lists = new ArrayList<PersonalActivity>();

		while (rs.next()) {
			lists.add(getBeanData(rs));
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

	private PersonalActivity getBeanData(ResultSet rs) throws Exception {
		PersonalActivity bean = new PersonalActivity();
		
		bean.setActivityId(rs.getInt("activityId"));
		bean.setUserId(rs.getString("userId"));
		bean.setActivityName(rs.getString("activityName"));
		bean.setCategory(rs.getString("category"));
		bean.setLocation(rs.getString("location"));
		bean.setLocationDetail(rs.getString("locationDetail"));
		bean.setReadHit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));

		return bean;
	}

	public int getPersonalTotalRecordCount(String mode, String keyword, String userId) throws Exception {
		System.out.println("검색 필드명 : " + mode + ", 검색 키워드 : " + keyword);

		String sql = " select count(*) as cnt from personal_activites ";
		sql += " where userId = ? ";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);

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
		if (conn != null) {
			conn.close();
		}

		return cnt;

	}



}
