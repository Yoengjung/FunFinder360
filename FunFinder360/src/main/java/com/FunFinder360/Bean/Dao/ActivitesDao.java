package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.ActivityAndImage;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Bean.Model.PersonalActivityDetail;

import Utility.Paging;

public class ActivitesDao extends SuperDao {

	public int insertPersonalActivityData(PersonalActivity personalActivity, List<String> contentList,
			List<String> imageList, String contentAndImageOrder) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into personal_activitis values (PERSONAL_ACTIVITY_SEQUENCE.nextval, ? ,? ,? ,? ,? ,? ,? ,? ,?, ?, default)";

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

		int contentOrder = 0;
		int imageOrder = 0;
		int totalOrder = 0;

		
		for (int j = 0; j < contentAndImageOrder.length(); j++) {
			char check = contentAndImageOrder.charAt(j);

			if (check == '0') {
				sql = "insert into activity_content (contentId, personalActivityId, content, contentOrder, totalOrder) values (activity_content_sequence.nextval, PERSONAL_ACTIVITY_SEQUENCE.currval, ?, ?, ?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, contentList.get(contentOrder));
				pstmt.setInt(2, contentOrder);
				pstmt.setInt(3, totalOrder);

				status = pstmt.executeUpdate();
				pstmt = null;
				totalOrder++;
				contentOrder++;
			} else if (check == '1') {
				sql = "insert into activity_image (imageId, personalActivityId, image, imageOrder, totalOrder) values (ACTIVITY_IMAGE_SEQUENCE.nextval, PERSONAL_ACTIVITY_SEQUENCE.currval, ?, ?, ?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, imageList.get(imageOrder));
				pstmt.setInt(2, imageOrder);
				pstmt.setInt(3, totalOrder);

				status = pstmt.executeUpdate();
				pstmt = null;
				totalOrder++;
				imageOrder++;
			}
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

		String sql = "select count(*) as cnt from personal_activitis";

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

		String sql = "select activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit from (select  activityId ,userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by readHit) as ranking from personal_activitis ac join activity_image im on ac.activityid = im.personalActivityId ";
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

		ActivitesList.setActivityId(rs.getInt("activityid"));
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

	// detail에서 사용하기 위한 데이터
	public PersonalActivityDetail getPersonalActivityData(int activityId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "";

		pstmt = conn.prepareStatement(sql);

		rs = pstmt.executeQuery();

		PersonalActivityDetail bean = new PersonalActivityDetail();
		if (rs.next()) {
			bean.setActivityId(rs.getInt("activityId"));
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

		return null;
	}

}
