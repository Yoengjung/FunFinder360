package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Bean.Model.OwnerActivityAndImage;

import Utility.Paging;

public class OwnerActivitesDao extends SuperDao {

	public int insertOwnerActivityData(OwnerActivity ownerActivity, List<String> contentList, List<String> imageList)
			throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into owner_activites(activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event) values (owner_activity_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, ownerActivity.getUserid());
		pstmt.setString(2, ownerActivity.getActivityName());
		pstmt.setString(3, ownerActivity.getCategory());
		pstmt.setString(4, ownerActivity.getLocation());
		pstmt.setString(5, ownerActivity.getLocationDetail());
		pstmt.setInt(6, ownerActivity.getDuration());
		pstmt.setInt(7, ownerActivity.getPrice());
		pstmt.setInt(8, ownerActivity.getActivitiyNumber());
		pstmt.setString(9, ownerActivity.getOpenTime());
		pstmt.setString(10, ownerActivity.getCloseTime());
		pstmt.setString(11, ownerActivity.getEvent());

		int status = -1;

		status = pstmt.executeUpdate();
		pstmt = null;

		int order = 0;
		for (int i = 0; i < contentList.size(); i++) {
			sql = "insert into activity_content (contentId, ownerActivityId, content, contentOrder) values (activity_content_sequence.nextval, owner_activity_sequence.currval, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contentList.get(i));
			pstmt.setInt(2, order);

			status = pstmt.executeUpdate();
			pstmt = null;

			order++;
		}

		order = 0;
		for (int i = 0; i < imageList.size(); i++) {
			sql = "insert into activity_image (imageId, ownerActivityId, image, imageOrder) values (ACTIVITY_IMAGE_SEQUENCE.nextval, owner_activity_sequence.currval, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imageList.get(i));
			pstmt.setInt(2, order);

			status = pstmt.executeUpdate();
			pstmt = null;

			order++;
		}

		conn.commit();

		if (conn != null) {
			conn.close();
		}
		return status;
	}

	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt from owner_activites ";

		if (mode == null || mode.equals("all")) {

		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}

		pstmt = connection.prepareStatement(sql);

		resultSet = pstmt.executeQuery();

		int cnt = -1;

		if (resultSet.next()) {
			cnt = resultSet.getInt("cnt");
		}

		if (resultSet != null) {
			resultSet.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (connection != null) {
			connection.close();
		}

		return cnt;
	}

	public List<OwnerActivityAndImage> selectAll(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = "select userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate from (select userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, Row_number() over(order by readhit) as ranking from owner_activites ow join activity_image im on ow.activityId = im.ownerActivityId ";
		if (mode == null || mode.equals("all")) {

		} else {
			sql += "where " + mode + " like '%" + keyword + "%'";
		}

		sql += ") " + " where ranking between ? and ? and imageorder = 0 ";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<OwnerActivityAndImage> bean = new ArrayList<OwnerActivityAndImage>();

		while (resultSet.next()) {
			bean.add(getActivityBeanData(resultSet));
		}

		if (resultSet != null) {
			resultSet.close();
		}
		if (prtmt != null) {
			prtmt.close();
		}
		if (connection != null) {
			connection.close();
		}
		return bean;
	}

	private OwnerActivityAndImage getActivityBeanData(ResultSet rs) throws Exception{
		OwnerActivityAndImage lists = new OwnerActivityAndImage();
		
		lists.setUserId(rs.getString("userId"));
		lists.setActivityName(rs.getString("activityName"));
		lists.setCategory(rs.getString("category"));
		lists.setLocation(rs.getString("location"));
		lists.setLocationDetail(rs.getString("locationDetail"));
		lists.setDuration(rs.getInt("duration"));
		lists.setPrice(rs.getInt("price"));
		lists.setOpenTime(rs.getString("openTime"));
		lists.setCloseTime(rs.getString("closeTime"));
		lists.setEvent(rs.getString("event"));
		lists.setImage(rs.getString("image"));
		lists.setReadHit(rs.getInt("readHit"));
		lists.setPostedDate(rs.getString("postedDate"));
		
		return lists;
	}

	public List<OwnerActivity> getSelectAll(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet rs = null;
		Connection connection = super.getConnection();

		String sql = " select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate ";
		sql += " from (select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, Row_number() over(order by readHit) as ranking ";
		sql += " from owner_activites ";
		
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
		
		if (mode == null || mode.equals("all")) {
		} else {
			sql += "where " + mode + " like '%" + keyword + "%'";
		}

		sql += " ) ";
		sql += " where ranking between ? and ? ";

		prtmt = connection.prepareStatement(sql);
		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		rs = prtmt.executeQuery();

		List<OwnerActivity> lists = new ArrayList<OwnerActivity>();

		while (rs.next()) {
			lists.add(ActivityBeanData(rs));
		}

		if (rs != null) {
			rs.close();
		}
		if (prtmt != null) {
			prtmt.close();
		}
		if (connection != null) {
			connection.close();
		}
		return lists;
	}

	private OwnerActivity ActivityBeanData(ResultSet rs) throws Exception{
		OwnerActivity bean = new OwnerActivity();

		bean.setActivityId(rs.getInt("activityId"));
		bean.setUserid(rs.getString("userid"));
		bean.setActivityName(rs.getString("activityName"));
		bean.setCategory(rs.getString("category"));
		bean.setLocation(rs.getString("location"));
		bean.setLocationDetail(rs.getString("locationDetail"));
		bean.setDuration(rs.getInt("duration"));
		bean.setPrice(rs.getInt("price"));
		bean.setActivitiyNumber(rs.getInt("activityNumber"));
		bean.setOpenTime(rs.getString("openTime"));
		bean.setCloseTime(rs.getString("closeTime"));
		bean.setEvent(rs.getString("event"));
		bean.setReadHit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));

		return bean;
	}

	public int getTotalRecordCount(Object mode, Object keyword) throws Exception{
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색할 키워드명 : " + keyword);

		String sql = " select count (*) as cnt from owner_activites ";
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

	public List<OwnerActivity> getDateByActivityId(int activityId, int totalRecodeCount) throws Exception{
		String updateSql = " update owner_activites set readHit = readHit + 1 where activityId = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		pstmt = conn.prepareStatement(updateSql);
		pstmt.setInt(1, activityId);

		pstmt.executeUpdate();
		List<OwnerActivity> lists = new ArrayList<OwnerActivity>();

		try {
			String sql = " select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, ";
			sql += "  ranking from (select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, rank() over(order by activityId asc) as ranking from owner_activites) where activityId = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, activityId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				lists.add(getBeanDataAndRanking(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		pstmt = null;
		int targetRanking = lists.get(0).getRanking();
		System.out.println("targetRanking : " + targetRanking);
		String sql = "";
		if (targetRanking <= 0) {
			sql = " select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, ranking from (select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, rank() over(order by activityId asc) as ranking from owner_activites) where ranking = ? ";
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
			sql = " select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, ranking from (select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, rank() over(order by activityId asc) as ranking from owner_activites) where ranking = ? ";
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
		if (targetRanking > 0 && targetRanking < totalRecodeCount) {
			sql = " select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, ranking from (select activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readHit, postedDate, rank() over(order by activityId asc) as ranking from owner_activites) where ranking in (?, ?) ";
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

	private OwnerActivity getBeanDataAndRanking(ResultSet rs) throws Exception{
		OwnerActivity bean = new OwnerActivity();

		bean.setActivityId(rs.getInt("activityId"));
		bean.setUserid(rs.getString("userid"));
		bean.setActivityName(rs.getString("activityName"));
		bean.setCategory(rs.getString("category"));
		bean.setLocation(rs.getString("location"));
		bean.setLocationDetail(rs.getString("locationDetail"));
		bean.setDuration(rs.getInt("duration"));
		bean.setPrice(rs.getInt("price"));
		bean.setActivitiyNumber(rs.getInt("activityNumber"));
		bean.setOpenTime(rs.getString("openTime"));
		bean.setCloseTime(rs.getString("closeTime"));
		bean.setEvent(rs.getString("event"));
		bean.setReadHit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));
		bean.setRanking(rs.getInt("ranking"));
		
		return bean;
	}

}
