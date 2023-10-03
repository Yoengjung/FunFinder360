package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.Favorites;

public class FavoritesDao extends SuperDao {

	public boolean insertFovoritesData(int activityId, String userId, int activityCheck) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into favorites(favoritesid, userId, personalActivityId, createDate) values (favorite_id_seq.nextval, ?, ?, default)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, userId);
		pstmt.setInt(2, activityId);

		int successCheck = -1;

		successCheck = pstmt.executeUpdate();

		boolean success = false;
		if (successCheck > 0) {
			success = true;
		}

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return success;
	}

	public List<Favorites> getFavoritesDataToUserId(String userId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();
		
		String sql = "select personalActivityId from favorites where userId = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		List<Favorites> favoritesList = new ArrayList<Favorites>();
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Favorites favorites = new Favorites();
			
			favorites.setPersonalActivityId(rs.getInt("personalActivityId"));
			
			favoritesList.add(favorites);
		}
		
		if (pstmt != null) {
			pstmt.close();
		}
		if(rs != null) {
			rs.close();
		}
		if (conn != null) {
			conn.close();
		}
		
		return favoritesList;
	}

}
