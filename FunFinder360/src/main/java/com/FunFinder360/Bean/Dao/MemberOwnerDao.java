package com.FunFinder360.Bean.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.MemberOwner;

public class MemberOwnerDao extends SuperDao {

	public MemberOwner getDataByPk(String id, String password) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select * from Owner_Users";
		sql += " where userid = ? and password = ?";

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, id);
		pstmt.setString(2, password);

		rs = pstmt.executeQuery();

		MemberOwner bean = null;
		if (rs.next()) {
			bean = getBeanData(rs);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (connection != null) {
			connection.close();
		}

		return bean;
	}

	private MemberOwner getBeanData(ResultSet rs) throws Exception {
		MemberOwner bean = new MemberOwner();

		bean.setUserId(rs.getString("userid"));
		bean.setPassword(rs.getString("password"));
		bean.setUserName(rs.getString("username"));
		bean.setBusinessName(rs.getString("BusinessName"));
		bean.setBusinessType(rs.getString("BusinessType"));
		bean.setBusinessNumber(Integer.valueOf(rs.getInt("BusinessNumber")));
		bean.setPhoneNumber(rs.getString("PhoneNumber"));
		bean.setEmail(rs.getString("email"));
		bean.setRegistrationDate(String.valueOf(rs.getDate("registrationDate")));

		return bean;
	}

	public int getTotalRecodeCount() throws Exception{
		String sql = " select count(*) as cnt from owner_users ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

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
		if (connection != null) {
			connection.close();
		}

		return cnt;
	}

	public List<MemberOwner> getMemberOwnerList() throws Exception{
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select * from owner_users order by username asc";
		
		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql) ;
		
		rs = pstmt.executeQuery() ;
		
		List<MemberOwner> lists = new ArrayList<MemberOwner>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs)) ;
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(connection != null) {connection.close();}
		
		return lists;
	}

}
