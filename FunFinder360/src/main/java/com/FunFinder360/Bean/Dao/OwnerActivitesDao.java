package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.ActivityAndImage;
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
		pstmt.setString(2, ownerActivity.getActivitiyName());
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

		String sql = "select count(*) as cnt from owner_activites";

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

	

}
