package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.Review;

import Utility.Paging;

public class ReviewDao extends SuperDao {
	public List<Review> selectAll(Paging pageInfo, int activityId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = " select re.reviewId, re.activityId, re.userid, re.rating, re.reviewcontent, re.revieworder, re.postedDate, per.username ";
		sql += " from reviews re join personal_users per on re.userid = per.userid ";
		sql += " where activityid = ? ";

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and  " + mode + " like '%" + keyword + "%'";
		}

		sql += " and re.reviewId between ? and ? ";

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activityId);
		pstmt.setInt(2, pageInfo.getBeginPage());
		pstmt.setInt(3, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<Review> lists = new ArrayList<Review>();

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

	private Review getBeanData(ResultSet rs) throws Exception {
		Review bean = new Review();

		bean.setUserName(rs.getString("userName"));
		bean.setRating(rs.getInt("rating"));
		bean.setReviewContent(rs.getString("reviewContent"));
		bean.setPostedDate(rs.getString("postedDate"));
		return bean;
	}

	public void insertReviewData(Review review) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into reviews values (reviews_sequence.nextval, ?, ?, ?, ?, ?, default)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, review.getActivityId());
		pstmt.setString(2, review.getUserId());
		pstmt.setInt(3, review.getRating());
		pstmt.setString(4, review.getReviewContent());
		pstmt.setInt(5, reviewToActivityIdCount(review.getActivityId()) + 1);

		pstmt.executeUpdate();

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public int reviewToActivityIdCount(int activityId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "select count(*) cnt from reviews where activityId = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, activityId);

		rs = pstmt.executeQuery();
		int cnt = -1;
		if (rs.next()) {
			cnt = rs.getInt("cnt");
		}

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return cnt;
	}

	public List<Review> getReviewDataToActivityId(int activityId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "select re.reviewId, re.activityId, re.userid, re.rating, re.reviewcontent, re.revieworder, re.postedDate, per.username from reviews re join personal_users per on re.userid = per.userid where activityid = ? order by re.reviewOrder desc";

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, activityId);

		rs = pstmt.executeQuery();

		List<Review> lists = new ArrayList<Review>();

		while (rs.next()) {
			lists.add(getReviewData(rs));
		}

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;

	}

	private Review getReviewData(ResultSet rs) throws Exception {
		Review review = new Review();

		review.setReviewId(rs.getInt("reviewId"));
		review.setActivityId(rs.getInt("activityId"));
		review.setUserId(rs.getString("userId"));
		review.setRating(rs.getInt("rating"));
		review.setReviewContent(rs.getString("reviewContent"));
		review.setReviewOrder(rs.getInt("reviewOrder"));
		review.setPostedDate(rs.getString("postedDate"));
		review.setUserName(rs.getString("userName"));

		return review;
	}

}
