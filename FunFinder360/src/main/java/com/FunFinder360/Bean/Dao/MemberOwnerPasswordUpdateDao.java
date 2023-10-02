package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MemberOwnerPasswordUpdateDao extends SuperDao {

	public boolean ownerupdatePassword(String name, String email, String id, String newPassword) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = " update owner_users set password = ? ";
		sql += " where userName = ? and email = ? and userId = ? ";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newPassword);
		pstmt.setString(2, name);
		pstmt.setString(3, email);
		pstmt.setString(4, id);

		pstmt.executeUpdate();
		conn.commit(); // 작업 성공 시 커밋
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
}
