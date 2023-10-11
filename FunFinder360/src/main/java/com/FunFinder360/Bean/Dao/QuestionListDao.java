package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.QuestionsList;

import Utility.Paging;

public class QuestionListDao extends SuperDao {

	public int InsertData(QuestionsList bean, String userId, int check) throws Exception {
		PreparedStatement pstmt = null;
		connection = super.getConnection();

		String sql = "";

		if (check == 0) {
			sql = "insert into questionList (questionListId, personalUserId, title, content) values (QUESTION_LIST_SEQUENCE.nextval, ?, ?, ?)";
		} else if (check == 1) {
			sql = "insert into questionList (questionListId, ownerUserId, title, content) values (QUESTION_LIST_SEQUENCE.nextval, ?, ?, ?)";
		}

		int cnt = -1;

		pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, userId);
		pstmt.setString(2, bean.getTitle());
		pstmt.setString(3, bean.getContent());

		cnt = pstmt.executeUpdate();

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
			String sql = " select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, respond, ranking from (select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, respond, rank() over(order by questionListId asc) as ranking from questionList) where questionListId = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, questionListId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				lists.add(getBeanData(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		pstmt = null;
		int targetRanking = lists.get(0).getRanking();
		System.out.println("targetRanking : " + targetRanking);
		String sql = "";
		if (targetRanking <= 1) {
			sql = " select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, respond, ranking from (select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, respond, rank() over(order by questionListId asc) as ranking from questionList) where ranking = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, targetRanking + 2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				lists.add(getBeanDataAndRanking(rs));
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

		}
		System.out.println(totalRecodeCount);
		if (targetRanking >= totalRecodeCount) {
			
			sql = " select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, ranking from (select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, rank() over(order by questionListId asc) as ranking from questionList) where ranking = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, totalRecodeCount - 1);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				lists.add(getBeanDataAndRanking(rs));
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

		}
		if (targetRanking > 0 && targetRanking < totalRecodeCount) {
			sql = " select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, ranking from (select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, rank() over(order by questionListId asc) as ranking from questionList) where ranking in (?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, targetRanking - 1);
			pstmt.setInt(2, targetRanking + 1);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lists.add(getBeanDataAndRanking(rs));
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

	private QuestionsList getBeanDataAndRanking(ResultSet rs) throws Exception{
		QuestionsList bean = new QuestionsList();
		
		bean.setQuestionListId(rs.getInt("questionListId"));
		bean.setPersonalUserId(rs.getString("personalUserId"));
		bean.setOwnerUserId(rs.getString("ownerUserId"));
		bean.setTitle(rs.getString("title"));
		bean.setContent(rs.getString("content"));
		bean.setReadhit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));
		bean.setRanking(rs.getInt("ranking"));
		
		return bean;
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

		String sql = " select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, respond, ranking ";
		sql += " from (select questionListId, personaluserid, owneruserId, title, content, readhit, postedDate, respond, rank() over(order by questionListId asc) as ranking ";
		sql += " from questionList";

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}

		sql += " ) ";
		sql += " where ranking between ? and ? ";

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
		bean.setRespond(rs.getString("respond"));
		bean.setRanking(rs.getInt("ranking"));
		
		return bean;
	}

	public void insertRespond(QuestionsList questionRespond) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "update questionlist set respond = ? ";
		sql += " where questionlistid = " + questionRespond.getQuestionListId();
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, questionRespond.getRespond());
		
		pstmt.executeUpdate();
		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		
	}

	public void insertRespond(QuestionsList questionRespond, int questionListId ) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "update questionlist set respond = ? ";
		sql += " where questionlistid = ? ";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, questionRespond.getRespond());
		pstmt.setInt(2, questionListId);
		
		
		pstmt.executeUpdate();
		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		
	}

}
