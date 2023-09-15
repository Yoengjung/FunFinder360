package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.FunFinder360.Bean.Model.PersonalActivity;

public class ActivitesDao extends SuperDao {

	public int insertPersonalActivityData(PersonalActivity personalActivity, List<String> contentList,
			List<String> imageList) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "insert into personal_activitis values (PERSONAL_ACTIVITY_SEQUENCE.nextval, ? ,? ,? ,? ,? ,? ,? ,? ,? )";

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

}
