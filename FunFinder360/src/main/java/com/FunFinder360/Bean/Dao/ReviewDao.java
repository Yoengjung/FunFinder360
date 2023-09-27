package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.FunFinder360.Bean.Model.Review;

public class ReviewDao extends SuperDao {

	public void insertReviewData(Review review) throws Exception{
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
	
	public int reviewToActivityIdCount(int activityId) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "select count(*) cnt from reviews where activityId = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, activityId);
		
		int cnt = pstmt.executeUpdate();
		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		
		return cnt;
	}

	public Review getReviewDataToActivityId(int activityId) {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		
		String sql = "select * cnt from reviews where activityId = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, activityId);
		
		pstmt.executeUpdate();
		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		
		return cnt;
		
	}
	
}
