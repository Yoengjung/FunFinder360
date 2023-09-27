package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MemberPasswordUpdateDao extends SuperDao {
	public boolean updatePassword(String name, String email, String id, String newPassword) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		try {
			String sql = " update personal_users set password = ? ";
			sql += " where username = ? and email = ? and userId = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);

			pstmt.executeUpdate();
			conn.commit(); // 작업 성공 시 커밋
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return true;

	}
}
