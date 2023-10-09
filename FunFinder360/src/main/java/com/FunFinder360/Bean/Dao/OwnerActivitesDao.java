package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.ContentObject;
import com.FunFinder360.Bean.Model.ImageObject;
import com.FunFinder360.Bean.Model.OwnerActivitesList;
import com.FunFinder360.Bean.Model.OwnerActivity;
import com.FunFinder360.Bean.Model.OwnerActivityAndImage;
import com.FunFinder360.Bean.Model.OwnerActivityDetail;

import Utility.Paging;

public class OwnerActivitesDao extends SuperDao {

	public int insertOwnerActivityData(OwnerActivity ownerActivity, List<String> contentList, List<String> imageList,
			String contentAndImageOrder) throws Exception {
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
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색 키워드 : " + keyword);
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

	public List<OwnerActivitesList> selectAll(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = "select activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, content from (select activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, Row_number() over(order by readhit) as ranking from owner_activites ow join activity_image im on ow.activityId = im.ownerActivityId where imageorder = 0) tt join activity_content con on tt.activityId = con.ownerActivityid";
		if (mode == null || mode.equals("all")) {

		} else {
			sql += "where " + mode + " like '%" + keyword + "%'";
		}

		sql += " where ranking between ? and ? ";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (resultSet.next()) {
			bean.add(getOwnerActivityBeanData(resultSet));
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

	private OwnerActivityAndImage getActivityBeanData(ResultSet rs) throws Exception {
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

	public OwnerActivityDetail getOwnerActivityData(int activityId) throws Exception {
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
				+ "    SELECT COUNT(*) AS b " + "    FROM activity_image " + "    where owneractivityid = ? " + ") B "
				+ "ON 1=1 ";
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

	public int GetTotalRecordCount() throws Exception {
		String sql = " select count(*) as cnt from owner_activites";

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

	public List<OwnerActivity> getOwnerActivityList(Paging pageInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		// String sql = " select * from owner_activites order by postedDate";
		String sql = " select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, TO_CHAR(posteddate, 'YYYY-MM-DD') AS posteddate ";
		sql += " from (select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, posteddate, RANK() OVER (ORDER BY activityId desc) AS ranking ";
		sql += " from owner_activites ";

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		sql += " ) ";
		sql += " where ranking between ? and ? ";

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<OwnerActivity> lists = new ArrayList<OwnerActivity>();

		while (rs.next()) {
			lists.add(getBeanData(rs));
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

		return lists;
	}

	private OwnerActivity getBeanData(ResultSet rs) throws Exception {
		OwnerActivity bean = new OwnerActivity();

		bean.setActivityId(rs.getInt("activityId"));
		bean.setUserid((rs.getString("userId")));
		bean.setActivitiyName(rs.getString("activityName"));
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

	public List<OwnerActivity> getOwnerUserToUserId(Paging pageInfo, String userId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = " select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, TO_CHAR(posteddate, 'YYYY-MM-DD') AS posteddate ";
		sql += " from (select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, posteddate, RANK() OVER (ORDER BY locationdetail ASC) AS ranking ";
		sql += " from owner_activites where userid = ? ";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";
		sql += " where ranking between ? AND ?";

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, userId);
		pstmt.setInt(2, pageInfo.getBeginRow());
		pstmt.setInt(3, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<OwnerActivity> lists = new ArrayList<OwnerActivity>();

		while (rs.next()) {

			lists.add(getBeanData(rs));
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

		return lists;
	}

	public int GetOwnerTotalRecordCount(String mode, String keyword, String userId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from (select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, posteddate ";
		sql += " from owner_activites ";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";
		sql += " where userid = ? ";

		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);

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

	public int GetLookTotalRecordCount(String mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM owner_activites ";

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

	public List<OwnerActivitesList> LookSelectAll(Paging pageInfo) throws Exception {

		PreparedStatement prtmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();

		String sql = " SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, content "
				+ "		FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY posteddate DESC) AS ranking "
				+ "		FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where imageorder = 0) tt join activity_content con on tt.activityId = con.ownerActivityId WHERE ranking BETWEEN ? AND ? ORDER BY ranking ";

		Connection conn = super.getConnection();

		prtmt = conn.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		rs = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (rs.next()) {
			bean.add(getOwnerActivityBeanData(rs));
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

		System.out.println("bean1 : " + bean);
		return bean;
	}

	public int GetOwnerCultureLookTotalRecordCount(String mode) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM owner_activites where category = '문화 - 엔터테인먼트'";

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

	public int GetOwnerCultureTotalRecordCount(String mode, String keyword) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from (select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, posteddate ";
		sql += " from owner_activites where category = '문화 - 엔터테인먼트'";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";

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

	public List<OwnerActivitesList> getOwnerCultureActivites(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;
		
		String mode = pageInfo.getMode();

		String sql = " SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate , content  "
				+ " FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY " + mode +" DESC) AS ranking  "
				+ "	FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where category = '문화 - 엔터테인먼트' AND imageorder = 0) tt join activity_content on activity_content.ownerActivityId = tt.activityid "
				+ " WHERE ranking BETWEEN ? AND ? ORDER BY " + mode + " desc ";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (resultSet.next()) {
			bean.add(getOwnerActivityBeanData(resultSet));
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

	private OwnerActivitesList getOwnerActivityBeanData(ResultSet rs) throws Exception{
		OwnerActivitesList bean = new OwnerActivitesList();
		
		bean.setActivityId(rs.getInt("activityId"));
		bean.setUserId((rs.getString("userId")));
		bean.setActivityName(rs.getString("activityName"));
		bean.setCategory(rs.getString("category"));
		bean.setLocation(rs.getString("location"));
		bean.setLocationDetail(rs.getString("locationDetail"));
		bean.setDuration(rs.getInt("duration"));
		bean.setPrice(rs.getInt("price"));
		bean.setOpenTime(rs.getString("openTime"));
		bean.setCloseTime(rs.getString("closeTime"));
		bean.setImage(rs.getString("image"));
		bean.setEvent(rs.getString("event"));
		bean.setReadHit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));
		bean.setContent(rs.getString("content"));
		
		return bean;
		
	}

	public List<OwnerActivitesList> getOwnerCultureSelectAll(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();

		String sql = " SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, readhit, postedDate, content "
				+ " FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY postedDate DESC) AS ranking "
				+ "	FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where category = '문화 - 엔터테인먼트' and imageorder = 0) tt join activity_content con on tt.activityid = con.ownerActivityid WHERE ranking BETWEEN ? AND ? ORDER BY ranking ";

		Connection conn = super.getConnection();

		prtmt = conn.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		rs = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (rs.next()) {
			bean.add(getOwnerActivityBeanData(rs));
		}

		if (rs != null) {
			rs.close();
		}
		if (prtmt != null) {
			prtmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		
		return bean;
	}

	public int GetOwnerFoodLookTotalRecordCount(String mode) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM owner_activites where category = '음식 - 요리'";

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

	public int GetOwnerFoodTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from (select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, posteddate ";
		sql += " from owner_activites where category = '음식 - 요리' ";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";

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

	public List<OwnerActivitesList> getOwnerFoodActivites(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();

		System.out.println("최신, 조회수 mode를 보기 : " + mode);

		String sql = "  SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content  ";
		sql += " FROM (SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate,ROW_NUMBER() OVER (ORDER BY " + mode + " deSC) AS ranking  ";
		sql += " FROM personal_activites ac JOIN activity_image im ON ac.activityid = im.personalActivityId ";
		sql += " WHERE category = '음식 - 요리' ) tt JOIN activity_content con ON tt.activityid = con.personalActivityid ";
		sql += " WHERE ranking BETWEEN ? AND ? ";
		sql += " ORDER BY " + mode + " desc ";


		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (resultSet.next()) {
			bean.add(getOwnerActivityBeanData(resultSet));
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

	public List<OwnerActivitesList> getOwnerFoodSelectAll(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = " select activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content ";
		sql += " from (select  activityId ,userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by postedDate desc) as ranking ";
		sql += " from personal_activites ac join activity_image im on ac.activityid = im.personalActivityId ";
		sql += " where category = '음식 - 요리' and imageorder = 0";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%' ";
		}
		sql += ") tt join activity_content con on tt.activityid = con.personalactivityId ";
		sql += " where ranking between ? and ?";

		Connection conn = super.getConnection();

		prtmt = conn.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		rs = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (rs.next()) {
			bean.add(getOwnerActivityBeanData(rs));
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
		
		return bean;
	}

	public int GetOwnerStudyLookTotalRecordCount(String mode) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM owner_activites where category = '교육 - 학습'";

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

	public int GetOwnerStudyTotalRecordCount(String mode, String keyword) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from (select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, posteddate ";
		sql += " from owner_activites where category = '교육 - 학습'";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";

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

	public List<OwnerActivitesList> getOwnerStudyActivites(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String sql = "SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate , content "
				+ "		 FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY "
				+ "				 postedDate DESC) AS ranking FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where category = '교육 - 학습' AND imageorder = 0) tt join activity_content on activity_content.ownerActivityId = tt.activityid "
				+ " WHERE ranking BETWEEN ? AND ? ORDER BY ranking";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (resultSet.next()) {
			bean.add(getOwnerActivityBeanData(resultSet));
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

	public List<OwnerActivitesList> getOwnerStudySelectAll(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();

		String sql = "SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, readhit, postedDate, content "
				+ "			   FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY postedDate DESC) AS ranking "
				+ "				FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where category = '교육 - 학습' and imageorder = 0) tt join activity_content con on tt.activityid = con.ownerActivityid WHERE ranking BETWEEN ? AND ? ORDER BY ranking ";

		Connection conn = super.getConnection();

		prtmt = conn.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		rs = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (rs.next()) {
			bean.add(getOwnerActivityBeanData(rs));
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
		
		return bean;
	}

	public int GetOwnerTravelLookTotalRecordCount(String mode) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM owner_activites where category = '여행 - 모험'";

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

	public int GetOwnerTravelTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from (select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, posteddate ";
		sql += " from owner_activites where category = '여행 - 모험'";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";

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

	public List<OwnerActivitesList> getOwnerTravelActivites(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String sql = "SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate , content "
				+ "		 FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY "
				+ "				 postedDate DESC) AS ranking FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where category = '여행 - 모험' AND imageorder = 0) tt join activity_content on activity_content.ownerActivityId = tt.activityid "
				+ " WHERE ranking BETWEEN ? AND ? ORDER BY ranking";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (resultSet.next()) {
			bean.add(getOwnerActivityBeanData(resultSet));
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

	public List<OwnerActivitesList> getOwnerTravelSelectAll(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();

		String sql = "SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, readhit, postedDate, content "
				+ "			   FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY postedDate DESC) AS ranking "
				+ "				FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where category = '여행 - 모험' and imageorder = 0) tt join activity_content con on tt.activityid = con.ownerActivityid WHERE ranking BETWEEN ? AND ? ORDER BY ranking ";

		Connection conn = super.getConnection();

		prtmt = conn.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		rs = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (rs.next()) {
			bean.add(getOwnerActivityBeanData(rs));
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
		
		return bean;
	}

	public int GetOwnerGameLookTotalRecordCount(String mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM owner_activites where category = '게임 - 취미'";

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

	public int GetOwnerGameTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from (select activityid, userId, activityName, category, location, locationdetail,duration, price, activitynumber,opentime, closetime, event, readhit, posteddate ";
		sql += " from owner_activites where category = '게임 - 취미'";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";

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

	public List<OwnerActivitesList> getOwnerGameActivites(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String sql = "SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate , content "
				+ "		 FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY "
				+ "				 postedDate DESC) AS ranking FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where category = '게임 - 취미' AND imageorder = 0) tt join activity_content on activity_content.ownerActivityId = tt.activityid "
				+ " WHERE ranking BETWEEN ? AND ? ORDER BY ranking";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (resultSet.next()) {
			bean.add(getOwnerActivityBeanData(resultSet));
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

	public List<OwnerActivitesList> getOwnerGameSelectAll(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();

		String sql = "SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, readhit, postedDate, content "
				+ "			   FROM (SELECT activityid, userid, activityname, category, location, LOCATIONDETAIL, duration, price, openTime, closeTime, event, image, imageorder, readhit, postedDate, ROW_NUMBER() OVER(ORDER BY postedDate DESC) AS ranking "
				+ "				FROM owner_activites ow JOIN activity_image im ON ow.activityId = im.ownerActivityId where category = '게임 - 취미' and imageorder = 0) tt join activity_content con on tt.activityid = con.ownerActivityid WHERE ranking BETWEEN ? AND ? ORDER BY ranking ";

		Connection conn = super.getConnection();

		prtmt = conn.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		rs = prtmt.executeQuery();

		List<OwnerActivitesList> bean = new ArrayList<OwnerActivitesList>();

		while (rs.next()) {
			bean.add(getOwnerActivityBeanData(rs));
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
		
		return bean;
	}

	public void deleteActivityData(int activityId) throws Exception{
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();

		String sql = "delete from owner_activites where activityId = ?";

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, activityId);
		
		pstmt.executeUpdate();

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
