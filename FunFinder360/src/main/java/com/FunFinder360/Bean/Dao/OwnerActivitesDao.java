package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.ContentObject;
import com.FunFinder360.Bean.Model.ImageObject;
import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Bean.Model.OwnerActivityAndImage;
import com.FunFinder360.Bean.Model.OwnerActivityDetail;

import Utility.Paging;

public class OwnerActivitesDao extends SuperDao {

	public int insertOwnerActivityData(OwnerActivity ownerActivity, List<String> contentList, List<String> imageList, String contentAndImageOrder)
			throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into owner_activites (activityId, userid, activityName, category, location, locationDetail, duration, price, activityNumber, openTime, closeTime, event, readhit, postedDate) values (OWNER_ACTIVITY_SEQUENCE.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default, default)";

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
			sql = "insert into activity_content (contentId, ownerActivityId, content, contentOrder) values (activity_content_sequence.nextval, OWNER_ACTIVITY_SEQUENCE.currval, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contentList.get(i));
			pstmt.setInt(2, order);

			status = pstmt.executeUpdate();
			pstmt = null;

			order++;
		}

		order = 0;
		for (int i = 0; i < imageList.size(); i++) {
			sql = "insert into activity_image (imageId, ownerActivityId, image, imageOrder) values (ACTIVITY_IMAGE_SEQUENCE.nextval, OWNER_ACTIVITY_SEQUENCE.currval, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imageList.get(i));
			pstmt.setInt(2, order);

			status = pstmt.executeUpdate();
			pstmt = null;

			order++;
		}

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

		String sql = "select activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate from (select activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, Row_number() over(order by readhit) as ranking from owner_activites ow join activity_image im on ow.activityId = im.ownerActivityId ";
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
		
		lists.setActivityId(rs.getInt("activityId"));
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

	public OwnerActivityDetail getOwnerActivityData(int activityId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();
		
		String sql = "update owner_activites set readHit = readHit + 1 where activityid = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activityId);
		pstmt.executeUpdate();
		
		pstmt = null;
		
		sql = "select * from owner_activites where activityid = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activityId);
		rs = pstmt.executeQuery();
		
		OwnerActivityDetail bean = new OwnerActivityDetail();
		
		if (rs.next()) {
			bean.setActivityId(rs.getInt("activityId"));
			bean.setUserId((rs.getString("userId")));
			bean.setActivityName(rs.getString("activityName"));
			bean.setCategory(rs.getString("category"));
			bean.setLocation(rs.getString("location"));
			bean.setLocationDetail(rs.getString("locationDetail"));
			bean.setDuration(rs.getInt("duration"));
			bean.setPrice(rs.getInt("price"));
			bean.setActivityNumber(rs.getInt("activityNumber"));
			bean.setOpenTime(rs.getString("openTime"));
			bean.setCloseTime(rs.getString("closeTime"));
			bean.setEvent(rs.getString("event"));
			bean.setReadHit(rs.getInt("readHit"));
			bean.setPostedDate(rs.getString("postedDate"));	
		}
		pstmt = null;
		
		sql = "select content, totalorder from activity_content where ownerActivityId = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activityId);
		rs = pstmt.executeQuery();
		
		ContentObject contentObject = null;
		while (rs.next()) {
			contentObject = new ContentObject();
			contentObject.setContent(rs.getString("content"));
			contentObject.setOrder(rs.getInt("totalorder"));

			bean.setContentList(contentObject);
		}

		pstmt = null;
		rs = null;

		sql = "select image, totalorder from activity_image where ownerActivityId = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activityId);
		rs = pstmt.executeQuery();

		ImageObject imageObject = null;

		while (rs.next()) {
			imageObject = new ImageObject();
			imageObject.setImage(rs.getString("image"));
			imageObject.setOrder(rs.getInt("totalorder"));

			bean.setImageList(imageObject);
		}
		sql = "SELECT (COALESCE(A.a, 0) + COALESCE(B.b, 0)) AS total_count " + "FROM ( " + "    SELECT COUNT(*) AS a"
				+ "    FROM activity_content " + "    where owneractivityid = ? " + ") A " + "FULL OUTER JOIN ( "
				+ "    SELECT COUNT(*) AS b " + "    FROM activity_image " + "    where owneractivityid = ? "
				+ ") B " + "ON 1=1 ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activityId);
		pstmt.setInt(2, activityId);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			bean.setTotalRacodeCount(rs.getInt("total_count"));
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

		return bean;
	}
}
