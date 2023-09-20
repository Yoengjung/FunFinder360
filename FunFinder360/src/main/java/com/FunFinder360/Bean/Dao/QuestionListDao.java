package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.QuestionsList;

import Utility.Paging;

public class QuestionListDao extends SuperDao {

	public int InsertData(QuestionsList bean, String userId) throws Exception{
		System.out.println(bean);
		PreparedStatement pstmt = null;
		String sql = " insert into questionList(questionListId, personalUserId, ownerUserId, title, content, readhit, postedDate)";
		sql += "         values(question_list_sequence.nextval, ?,              ?,           ?,     ?,       ?,       ?)";
		int cnt = -1;

		connection = super.getConnection();
		connection.setAutoCommit(false);

		pstmt = connection.prepareStatement(sql);
		
		if(check==1) {
			pstmt.setString(1, userId);
			pstmt.setString(2, bean.getOwnerUserId());
		}else {
			pstmt.setString(1, bean.getPersonalUserId());
			pstmt.setString(2, userId);
		}
		pstmt.setString(3, bean.getTitle());
		pstmt.setString(4, bean.getContent());
		pstmt.setInt(5, bean.getReadhit());
		pstmt.setString(6, bean.getPostedDate());

		cnt = pstmt.executeUpdate();
		connection.commit();

		if (pstmt != null) {
			pstmt.close();
		}
		if (connection != null) {
			connection.close();
		}
		return cnt;
	
	}

	public List<QuestionsList> getDateByQuestionId(int questionListId, int totalRecodeCount) throws Exception {

		String updateSql = " update questionList set readhit = readhit + 1 where questionListId = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		pstmt = conn.prepareStatement(updateSql);
		pstmt.setInt(1, questionListId);

		pstmt.executeUpdate();
		List<QuestionsList> lists = new ArrayList<QuestionsList>();

		try {
			String sql = " select * from questionList where questionListId = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, questionListId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				lists.add(getBeanData(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql = "";
		if (questionListId <= 1) {
			sql = " select * from questionList where questionListId = ? ";
			pstmt.setInt(1, questionListId + 1);
		} else if (questionListId > totalRecodeCount) {
			sql = " select  from questionList where questionListId = ? ";
			pstmt.setInt(1, questionListId - 1);

		} else {
			sql = " select * from (select * from questionList order by questionListId) where questionListid in (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questionListId - 1);
			pstmt.setInt(2, questionListId + 1);
		}

		rs = pstmt.executeQuery();

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

	public int getTotalRecordCount(Object mode, Object keyword) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색할 키워드명 : " + keyword);

		String sql = " select count (*) as cnt from questionList ";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);

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

	public List<QuestionsList> getSelectAll(Paging pageInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();
		
		String sql = " select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate ";
		sql += " from (select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, rank() over(order by questionListId asc) as ranking ";
		sql += " from questionList";
		

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}

		sql += " ) ";
		sql += " where ranking between ? and ? ";
		
		System.err.println("오류");

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		
		rs = pstmt.executeQuery();

		List<QuestionsList> lists = new ArrayList<QuestionsList>();
		
		
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

	private QuestionsList getBeanData(ResultSet rs) throws Exception {
		QuestionsList bean = new QuestionsList();

		bean.setQuestionListId(rs.getInt("questionListId"));
		bean.setPersonalUserId(rs.getString("personalUserId"));
		bean.setOwnerUserId(rs.getString("ownerUserId"));
		bean.setTitle(rs.getString("title"));
		bean.setContent(rs.getString("content"));
		bean.setReadhit(rs.getInt("readhit"));
		bean.setPostedDate(rs.getString("postedDate"));

		return bean;
	}

}
