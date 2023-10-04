package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.ActivityAndImage;
import com.FunFinder360.Bean.Model.PersonalActivity;

import Utility.Paging;

public class ActivitesDao extends SuperDao {

	public int insertPersonalActivityData(PersonalActivity personalActivity, List<String> contentList,
			List<String> imageList) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into personal_activites values (PERSONAL_ACTIVITY_SEQUENCE.nextval, ? ,? ,? ,? ,? ,? ,? ,? ,?, ?, default)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, personalActivity.getUserId());
		pstmt.setString(2, personalActivity.getActivityName());
		pstmt.setString(3, personalActivity.getCategory());
		pstmt.setString(4, personalActivity.getLocation());
		pstmt.setString(5, personalActivity.getLocationDetail());
		pstmt.setInt(6, personalActivity.getDuration());
		pstmt.setInt(7, personalActivity.getCost());
		pstmt.setInt(8, personalActivity.getActivityNumber());
		pstmt.setInt(9, personalActivity.getRating());
		pstmt.setInt(10, personalActivity.getReadHit());

		int status = -1;

		status = pstmt.executeUpdate();
		pstmt = null;

		int order = 0;
		for (int i = 0; i < contentList.size(); i++) {
			sql = "insert into activity_content (contentId, personalActivityId, content, contentOrder) values (activity_content_sequence.nextval, PERSONAL_ACTIVITY_SEQUENCE.currval, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contentList.get(i));
			pstmt.setInt(2, order);

			status = pstmt.executeUpdate();
			pstmt = null;

			order++;
		}

		order = 0;
		for (int i = 0; i < imageList.size(); i++) {
			sql = "insert into activity_image (imageId, personalActivityId, image, imageOrder) values (ACTIVITY_IMAGE_SEQUENCE.nextval, PERSONAL_ACTIVITY_SEQUENCE.currval, ?, ?)";

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

		String sql = "select count(*) as cnt from personal_activites";

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

	public List<ActivityAndImage> selectAll(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = "select userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit from (select userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by readHit) as ranking from personal_activites ac join activity_image im on ac.activityid = im.personalActivityId ";
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

		List<ActivityAndImage> bean = new ArrayList<ActivityAndImage>();

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

	private ActivityAndImage getActivityBeanData(ResultSet rs) throws Exception {
		ActivityAndImage ActivitesList = new ActivityAndImage();
		
		ActivitesList.setUserId(rs.getString("userId"));
		ActivitesList.setActivityName(rs.getString("activityName"));
		ActivitesList.setCategory(rs.getString("category"));
		ActivitesList.setLocation(rs.getString("location"));
		ActivitesList.setLocationDetail(rs.getString("locationDetail"));
		ActivitesList.setImage(rs.getString("image"));
		ActivitesList.setImageOrder(rs.getInt("imageOrder"));
		ActivitesList.setReadHit(rs.getInt("readhit"));

		return ActivitesList;
	}

}
