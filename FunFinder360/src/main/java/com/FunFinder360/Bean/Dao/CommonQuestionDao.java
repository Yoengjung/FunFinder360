package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.CommonQuestion;

import Utility.Paging;

public class CommonQuestionDao extends SuperDao {

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

			bean.setQuestion_id(rs.getInt("question_id"));
			bean.setUserId(rs.getString("user_id"));
			bean.setTitle(rs.getString("title"));
			bean.setContent(rs.getString("content"));
			bean.setPostedDate(rs.getString("posted_date"));
			bean.setReadhit(rs.getInt("readHit"));

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

		bean.setQuestion_id(rs.getInt("question_id"));
		bean.setUserId(rs.getString("user_id"));
		bean.setTitle(rs.getString("title"));
		bean.setContent(rs.getString("content"));
		bean.setPostedDate(rs.getString("posted_date"));
		bean.setReadhit(rs.getInt("readHit"));

		return bean;
	}

	public List<CommonQuestion> getSelectAll(Paging pageInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = " select question_id, user_id, title, content, posted_date, readhit "
				+ "from (select question_id, user_id, title, content, posted_date, readhit, rank() over(order by question_id asc) as ranking "
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

	public CommonQuestion getDataByQuestionId(int qeustion_id) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();
		
		String sql = "select * from common_question where question_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, qeustion_id);
		
		rs = pstmt.executeQuery();
		
		CommonQuestion bean = null;
		
		if(rs.next()) {
			bean = getBeanData(rs);
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
		return bean;
	}

}
