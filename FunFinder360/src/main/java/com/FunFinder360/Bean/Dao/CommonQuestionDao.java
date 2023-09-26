package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.CommonQuestion;

import Utility.Paging;

public class CommonQuestionDao extends SuperDao {
	
	public int DeleteDate(int questionId) throws Exception {
		int cnt = -1;
		PreparedStatement pstmt = null;
		connection = super.getConnection();
		connection.setAutoCommit(false);
		
		String sql = " delete from common_question where questionId = ?";
		pstmt = connection.prepareStatement(sql);
		
		pstmt.setInt(1, questionId);
		
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

	public List<CommonQuestion> getCommonQuestionData() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "select * from common_question";

		pstmt = conn.prepareStatement(sql);

		rs = pstmt.executeQuery();

		List<CommonQuestion> lists = new ArrayList<CommonQuestion>();

		while (rs.next()) {
			CommonQuestion bean = new CommonQuestion();

			bean.setQuestionId(rs.getInt("questionId"));
			bean.setUserId(rs.getString("userId"));
			bean.setTitle(rs.getString("title"));
			bean.setContent(rs.getString("content"));
			bean.setReadhit(rs.getInt("readHit"));
			bean.setPostedDate(rs.getString("postedDate"));

			lists.add(bean);
		}

		if (pstmt != null) {
			pstmt.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;
	}

	public int getTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.print("검색할 필드명 : " + mode);
		System.out.println(", 검색할 키워드 : " + keyword);

		// 테이블의 총 행개수를 구합니다.
		String sql = " select count(*) as cnt from common_question";
		if (mode == null || mode.equals("all")) {
		} else { // 전체 모드가 아니면
			sql += " where " + mode + " like '%" + keyword + "%'";
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();

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

	private CommonQuestion getBeanData(ResultSet rs) throws Exception {
		CommonQuestion bean = new CommonQuestion();

		bean.setQuestionId(rs.getInt("questionId"));
		bean.setUserId(rs.getString("userId"));
		bean.setTitle(rs.getString("title"));
		bean.setContent(rs.getString("content"));
		bean.setReadhit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));

		return bean;
	}

	public List<CommonQuestion> getSelectAll(Paging pageInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = " select questionId, userId, title, content, readhit, postedDate "
				+ "from (select questionId, userId, title, content, readhit, postedDate, rank() over(order by questionId asc) as ranking "
				+ "from common_question";

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		if (mode == null || mode.equals("all")) {
		} else { // 전체 모드가 아니면
			sql += " where " + mode + " like '%" + keyword + "%'";
		}

		sql += " ) ";
		sql += " where ranking between ? and ? ";

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<CommonQuestion> lists = new ArrayList<CommonQuestion>();

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

	public List<CommonQuestion> getDataByQuestionId(int question_id, int totalRecodeCount) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String updateSql = "update common_question set readHit = readhit + 1 where questionId = ?";

		pstmt = conn.prepareStatement(updateSql);

		pstmt.setInt(1, question_id);

		pstmt.executeUpdate();

		List<CommonQuestion> lists = new ArrayList<CommonQuestion>();

		try {
			String sql = "select questionid, userid, title, content, readhit, postedDate, ranking from (select questionId, userid, title, content, readhit, postedDate, rank() over(order by questionid) as ranking from common_question) where questionid = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, question_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				lists.add(getBeanDataAndRanking(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pstmt = null;
		int targetRanking = lists.get(0).getRanking();
		String sql = "";
		if (targetRanking <= 1) {
			sql = "select questionid, userid, title, content, readhit, postedDate, ranking from (select questionId, userid, title, content, readhit, postedDate, rank() over(order by questionid) as ranking from common_question) where ranking = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, targetRanking + 1);
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
		if (targetRanking >= totalRecodeCount) {
			sql = "select questionid, userid, title, content, readhit, postedDate, ranking from (select questionId, userid, title, content, readhit, postedDate, rank() over(order by questionid) as ranking from common_question) where ranking = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, targetRanking - 1);
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
		if (targetRanking > 1 && targetRanking < totalRecodeCount) {
			sql = "select questionid, userid, title, content, readhit, postedDate, ranking from (select questionId, userid, title, content, readhit, postedDate, rank() over(order by questionid) as ranking from common_question) where ranking in (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, targetRanking - 1);
			pstmt.setInt(2, targetRanking + 1);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lists.add(getBeanDataAndRanking(rs));
			}

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}

		if (conn != null) {
			conn.close();
		}
		return lists;
	}

	private CommonQuestion getBeanDataAndRanking(ResultSet rs) throws Exception {
		CommonQuestion bean = new CommonQuestion();

		bean.setQuestionId(rs.getInt("questionId"));
		bean.setUserId(rs.getString("userId"));
		bean.setTitle(rs.getString("title"));
		bean.setContent(rs.getString("content"));
		bean.setReadhit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));
		bean.setRanking(rs.getInt("ranking"));

		return bean;
	}

	public int InsertData(CommonQuestion bean, String string) throws Exception {
		System.out.println(bean);
		PreparedStatement pstmt = null;
		String sql = " insert into common_question(questionId, userId, title, content)";
		sql += "          					values(COMMON_QUESTION_SEQUENCE.nextval, ?, ?, ?)";
		int cnt = -1;

		connection = super.getConnection();
		connection.setAutoCommit(false);

		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, bean.getUserId());
		pstmt.setString(2, bean.getTitle());
		pstmt.setString(3, bean.getContent());

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

	

}
