package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.CommonQuestion;
import com.FunFinder360.Bean.Model.PersonalActivity;

import Utility.Paging;

public class MemberActivitesDao extends SuperDao {
	public List<PersonalActivity> getPseronalSelectAll(Paging pageInfo, String personal_id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "select activityname, category, location, locationDetail, readhit, postedDate";
		sql += " from (select activityname, category, location, locationDetail, readhit, postedDate, rank() over(order by activityname asc) as ranking";
		sql += " from personal_activitis where userid = ?";

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
		}
		System.out.println("오류 확인중");

		sql += " ) ";
		sql += " where ranking between ? and ?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, personal_id);
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

	private PersonalActivity getBeanData(ResultSet rs) throws Exception{
		PersonalActivity bean = new PersonalActivity();
		
		bean.setActivityName(rs.getString("activityName"));
		bean.setCategory(rs.getString("category"));
		bean.setLocation(rs.getString("location"));
		bean.setLocationDetail(rs.getString("locationDetail"));
		bean.setReadHit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));
		
		return bean;
	}

	public int getPersonalTotalRecordCount(String mode, String keyword, String personal_id) throws Exception {
		System.out.println("검색 필드명 : " + mode + ", 검색 키워드 : " + keyword);

		String sql = " select count(*) as cnt from personal_activitis ";
		sql += " where userId = ? ";
		if (mode == null || mode.equals("all")) {
		}else {
			sql += " and " + mode + " like '%" + keyword + "%'";
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, personal_id);

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
