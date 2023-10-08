package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FunFinder360.Bean.Model.ActivityAndImage;
import com.FunFinder360.Bean.Model.ContentObject;
import com.FunFinder360.Bean.Model.ImageObject;
import com.FunFinder360.Bean.Model.PersonalActivitesList;
import com.FunFinder360.Bean.Model.PersonalActivity;
import com.FunFinder360.Bean.Model.PersonalActivityDetail;

import Utility.Paging;

public class ActivitesDao extends SuperDao {

	public int insertPersonalActivityData(PersonalActivity personalActivity, List<String> contentList,
			List<String> imageList, String contentAndImageOrder) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = super.getConnection();
		conn.setAutoCommit(false);

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

	public int GetTotalRecordCount(String mode, String keyword, String userId) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검새 키워드명 : " + keyword);

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from ( SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate";
		sql += " from personal_activites ";

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
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

	public List<ActivityAndImage> selectAll(Paging pageInfo) throws Exception {
		System.out.println("사실은 여기임.");
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = "select activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate from (select  activityId ,userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by readHit desc) as ranking from personal_activites ac join activity_image im on ac.activityid = im.personalActivityId ";
		if (mode == null || mode.equals("all")) {

		} else {
			sql += "where " + mode + " like '%" + keyword + "%'";
		}

		sql += ") " + " where ranking between ? and ?";

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
		ActivitesList.setPostedDate(rs.getString("postedDate"));

		return ActivitesList;
	}

	// detail에서 사용하기 위한 데이터
	public PersonalActivityDetail getPersonalActivityData(int activityId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = super.getConnection();

		String sql = "update personal_activites set readHit = readHit + 1 where activityid = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activityId);
		pstmt.executeUpdate();

		pstmt = null;

		sql = "select * from personal_activites where activityid = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activityId);
		rs = pstmt.executeQuery();

		PersonalActivityDetail bean = new PersonalActivityDetail();
		if (rs.next()) {
			bean.setActivityId(rs.getInt("ACTIVITYID"));
			bean.setUserId(rs.getString("userid"));
			bean.setActivityName(rs.getString("ACTIVITYNAME"));
			bean.setCategory(rs.getString("category"));
			bean.setLocation(rs.getString("location"));
			bean.setLocationDetail(rs.getString("locationDetail"));
			bean.setDuration(rs.getInt("duration"));
			bean.setCost(rs.getInt("cost"));
			bean.setActivityNumber(rs.getInt("activityNumber"));
			bean.setRating(rs.getInt("rating"));
			bean.setReadHit(rs.getInt("readHit"));
			bean.setPostedDate(rs.getString("postedDate"));
		}
		pstmt = null;

		sql = "select content, totalorder from activity_content where personalactivityid = ? ";
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

		sql = "select image, totalorder from activity_image where personalActivityid = ? ";
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

		pstmt = null;

		sql = "SELECT (COALESCE(A.a, 0) + COALESCE(B.b, 0)) AS total_count " + "FROM ( " + "    SELECT COUNT(*) AS a"
				+ "    FROM activity_content " + "    where personalactivityid = ? " + ") A " + "FULL OUTER JOIN ( "
				+ "    SELECT COUNT(*) AS b " + "    FROM activity_image " + "    where personalactivityid = ? "
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

	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검새 키워드명 : " + keyword);

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from ( SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate";
		sql += " from personal_activites ";

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
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

	public List<PersonalActivity> getMemberPersonalList(Paging pageInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = " SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, TO_CHAR(posteddate, 'YYYY-MM-DD') AS posteddate, ranking ";
		sql += " FROM (SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate, RANK() OVER (ORDER BY activityId desc) AS ranking";
		sql += " FROM personal_activites";
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " where " + mode + " like '%" + keyword + "%' ";
		}
		sql += " ) ";
		sql += " where ranking between ? AND ? ";

		connection = super.getConnection();
		pstmt = connection.prepareStatement(sql);

		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<PersonalActivity> lists = new ArrayList<PersonalActivity>();

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

	private PersonalActivity getBeanData(ResultSet rs) throws Exception {
		PersonalActivity bean = new PersonalActivity();

		bean.setActivityId(rs.getInt("ACTIVITYID"));
		bean.setUserId(rs.getString("userid"));
		bean.setActivityName(rs.getString("ACTIVITYNAME"));
		bean.setCategory(rs.getString("category"));
		bean.setLocation(rs.getString("location"));
		bean.setLocationDetail(rs.getString("locationDetail"));
		bean.setDuration(rs.getInt("duration"));
		bean.setCost(rs.getInt("cost"));
		bean.setActivityNumber(rs.getInt("activityNumber"));
		bean.setRating(rs.getInt("rating"));
		bean.setReadHit(rs.getInt("readHit"));
		bean.setPostedDate(rs.getString("postedDate"));

		return bean;
	}

	public List<PersonalActivity> getPersonalUserToUserId(Paging pageInfo, String userId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = " SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, TO_CHAR(posteddate, 'YYYY-MM-DD') AS posteddate, ranking ";
		sql += " FROM (SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate, RANK() OVER (ORDER BY rating ASC) AS ranking";
		sql += " FROM personal_activites where userId = ?";
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

		List<PersonalActivity> lists = new ArrayList<PersonalActivity>();

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

	public int GetPersonalTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM personal_activites ";

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

	public int GetLookTotalRecordCount(String mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM personal_activites where category = '문화 - 엔터테인먼트' ";

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

	public List<ActivityAndImage> LookSelectAll(Paging pageInfo) throws Exception {
		System.out.println("지금 실행중입니다.");
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();

		String sql = " SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate "
				+ " FROM (SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, "
				+ " Row_number() over(order by " + mode + " desc) as ranking "
				+ " FROM personal_activites ac JOIN activity_image im ON ac.activityid = im.personalActivityId) "
				+ " WHERE ranking BETWEEN ? AND ?";

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

		System.out.println("bean1 : " + bean);
		return bean;
	}

	public List<PersonalActivitesList> getCultureActivites(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;
		String mode = pageInfo.getMode();
		
		
		System.out.println("최신, 조회수 mode를 보기 : " + mode);

		String sql = "  SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content  ";
		sql += " FROM (SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate,ROW_NUMBER() OVER (ORDER BY activityId deSC) AS ranking  ";
		sql	+= " FROM personal_activites ac JOIN activity_image im ON ac.activityid = im.personalActivityId ";
		sql += " WHERE category = '문화 - 엔터테인먼트' ) tt JOIN activity_content con ON tt.activityid = con.personalActivityid ";
		sql	+= " WHERE ranking BETWEEN ? AND ? ";
		sql	+= " ORDER BY " + mode + " desc ";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

	private PersonalActivitesList getPersonalActivityBeanData(ResultSet rs) throws SQLException {
		PersonalActivitesList bean = new PersonalActivitesList();

		bean.setActivityId(rs.getInt("activityid"));
		bean.setUserId(rs.getString("userId"));
		bean.setActivityName(rs.getString("activityName"));
		bean.setCategory(rs.getString("category"));
		bean.setLocation(rs.getString("location"));
		bean.setLocationDetail(rs.getString("locationDetail"));
		bean.setImage(rs.getString("image"));
		bean.setImageOrder(rs.getInt("imageOrder"));
		bean.setReadHit(rs.getInt("readhit"));
		bean.setPostedDate(rs.getString("postedDate"));
		bean.setContent(rs.getString("content"));

		return bean;
	}

	public int GetCultureLookTotalRecordCount(String mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM personal_activites where category = '문화 - 엔터테인먼트'";

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

	public int GetCultureTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from ( SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate";
		sql += " from personal_activites where category = '문화 - 엔터테인먼트' ";

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
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

	public List<PersonalActivitesList> getCultureSelectAll(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql  = " select activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content ";
		sql += " from (select  activityId ,userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by postedDate desc) as ranking ";
		sql += " from personal_activites ac join activity_image im on ac.activityid = im.personalActivityId ";
		sql += " where category = '문화 - 엔터테인먼트' and imageorder = 0" ;
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%' ";
		}	
		sql	+=  ") tt join activity_content con on tt.activityid = con.personalactivityId ";
		sql += " where ranking between ? and ?";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

		System.out.println("bean1 : " + bean);
		return bean;
	}

	public int GetFoodLookTotalRecordCount(String mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM personal_activites where category = '음식 - 요리'";

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

	public int GetFoodTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from ( SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate";
		sql += " from personal_activites where category = '음식 - 요리'";

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
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

	public List<PersonalActivitesList> getFoodActivites(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String sql = "  SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content "
				+ "                        FROM (SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, "
				+ "                        Row_number() over(order by activityId desc) as ranking  "
				+ "                        FROM personal_activites ac JOIN activity_image im ON ac.activityid = im.personalActivityId where category= '음식 - 요리') tt join activity_content con on tt.activityid = con.personalActivityid "
				+ "                        WHERE ranking BETWEEN ? AND ? ";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

	public List<PersonalActivitesList> getFoodSelectAll(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql = "select activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content from (select  activityId ,userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by postedDate desc) as ranking from personal_activites ac join activity_image im on ac.activityid = im.personalActivityId where category = '음식 - 요리') tt join activity_content con on tt.activityid = con.personalactivityId ";
		if (mode == null || mode.equals("all")) {

		} else {
			sql += "where " + mode + " like '%" + keyword + "%' ";
		}

		sql += " where ranking between ? and ?";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

	public int GetStudyLookTotalRecordCount(String mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM personal_activites where category = '교육 - 학습'";

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

	public int GetStudyTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from ( SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate";
		sql += " from personal_activites where category = '교육 - 학습' ";

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
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

	public List<PersonalActivitesList> getStudyActivites(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();

		System.out.println("최신, 조회수 mode를 보기 : " + mode);

		String sql = "  SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content  ";
		sql += " FROM (SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate,ROW_NUMBER() OVER (ORDER BY activityId deSC) AS ranking  ";
		sql += " FROM personal_activites ac JOIN activity_image im ON ac.activityid = im.personalActivityId ";
		sql += " WHERE category = '교육 - 학습' ) tt JOIN activity_content con ON tt.activityid = con.personalActivityid ";
		sql += " WHERE ranking BETWEEN ? AND ? ";
		sql += " ORDER BY " + mode + " desc ";
		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

	public List<PersonalActivitesList> getStudySelectAll(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql  = " select activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content ";
		sql += " from (select  activityId ,userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by postedDate desc) as ranking ";
		sql += " from personal_activites ac join activity_image im on ac.activityid = im.personalActivityId ";
		sql += " where category = '교육 - 학습' and imageorder = 0" ;
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%' ";
		}	
		sql	+=  ") tt join activity_content con on tt.activityid = con.personalactivityId ";
		sql += " where ranking between ? and ?";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

	public int GetTravelLookTotalRecordCount(String mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM personal_activites where category = '여행 - 모험'";

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

	public int GetTravelTotalRecordCount(String mode, String keyword) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from ( SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate";
		sql += " from personal_activites where category = '여행 - 모험' ";

		if (mode == null || mode.equals("all")) {
		} else {
<<<<<<< HEAD
			sql += " and " + mode + " like '%" + keyword + "%'";
=======
			sql += " and " + mode + " like '%" + keyword + "%'" ;
>>>>>>> a22826cde19333d362ed56cd7f72f4820cce6506
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

	public List<PersonalActivitesList> getTravelActivites(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

String mode = pageInfo.getMode();
		
		
		System.out.println("최신, 조회수 mode를 보기 : " + mode);

		String sql = "  SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content  ";
		sql += " FROM (SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate,ROW_NUMBER() OVER (ORDER BY activityId deSC) AS ranking  ";
		sql	+= " FROM personal_activites ac JOIN activity_image im ON ac.activityid = im.personalActivityId ";
		sql += " WHERE category = '여행 - 모험' ) tt JOIN activity_content con ON tt.activityid = con.personalActivityid ";
		sql	+= " WHERE ranking BETWEEN ? AND ? ";
		sql	+= " ORDER BY " + mode + " desc ";
		
		
		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

	public List<PersonalActivitesList> getTravalSelectAll(Paging pageInfo) throws Exception {
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql  = " select activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content ";
		sql += " from (select  activityId ,userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by postedDate desc) as ranking ";
		sql += " from personal_activites ac join activity_image im on ac.activityid = im.personalActivityId ";
		sql += " where category = '여행 - 모험' and imageorder = 0" ;
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%' ";
		}	
		sql	+=  ") tt join activity_content con on tt.activityid = con.personalactivityId ";
		sql += " where ranking between ? and ?";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

	public int GetGameLookTotalRecordCount(String mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " SELECT count(*) as cnt FROM personal_activites where category = '게임 - 취미'";

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

	public int GetGameTotalRecordCount(String mode, String keyword) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = super.getConnection();

		String sql = " select count(*) as cnt ";
		sql += " from ( SELECT activityid, userid, activityname, category, location, locationdetail, duration, cost, activitynumber, rating, readhit, posteddate";
<<<<<<< HEAD
		sql += " from personal_activites where category = '게임 - 취미'";
=======
		sql += " from personal_activites where category = '게임 - 취미' ";
>>>>>>> a22826cde19333d362ed56cd7f72f4820cce6506

		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
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

	public List<PersonalActivitesList> getGameActivites(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;
		
		String mode = pageInfo.getMode();

		System.out.println("최신, 조회수 mode를 보기 : " + mode);

		String sql = "  SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content  ";
		sql += " FROM (SELECT activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate,ROW_NUMBER() OVER (ORDER BY activityId deSC) AS ranking  ";
		sql	+= " FROM personal_activites ac JOIN activity_image im ON ac.activityid = im.personalActivityId ";
		sql += " WHERE category = '게임 - 취미' ) tt JOIN activity_content con ON tt.activityid = con.personalActivityid ";
		sql	+= " WHERE ranking BETWEEN ? AND ? ";
		sql	+= " ORDER BY " + mode + " desc ";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

	public List<PersonalActivitesList> getGameSelectAll(Paging pageInfo) throws Exception{
		PreparedStatement prtmt = null;
		ResultSet resultSet = null;

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		String sql  = " select activityId, userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, content ";
		sql += " from (select  activityId ,userid, activityname, category, location, LOCATIONDETAIL, image, imageorder, readhit, postedDate, Row_number() over(order by postedDate desc) as ranking ";
		sql += " from personal_activites ac join activity_image im on ac.activityid = im.personalActivityId ";
		sql += " where category = '게임 - 취미' and imageorder = 0" ;
		if (mode == null || mode.equals("all")) {
		} else {
			sql += " and " + mode + " like '%" + keyword + "%' ";
		}	
		sql	+=  ") tt join activity_content con on tt.activityid = con.personalactivityId ";
		sql += " where ranking between ? and ?";

		Connection connection = super.getConnection();

		prtmt = connection.prepareStatement(sql);

		prtmt.setInt(1, pageInfo.getBeginRow());
		prtmt.setInt(2, pageInfo.getEndRow());

		resultSet = prtmt.executeQuery();

		List<PersonalActivitesList> bean = new ArrayList<PersonalActivitesList>();

		while (resultSet.next()) {
			bean.add(getPersonalActivityBeanData(resultSet));
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

}
