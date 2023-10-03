package com.FunFinder360.Controller.FavoritesController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.FunFinder360.Bean.Dao.FavoritesDao;
import com.FunFinder360.Bean.Model.Favorites;
import com.FunFinder360.Controller.SuperClass;

public class FavoritesInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		super.doGet(request, response);
		
		String userId = request.getParameter("userId");
		
		List<Favorites> favorites = null;
		
		FavoritesDao dao = new FavoritesDao();
		
		try {
			favorites = dao.getFavoritesDataToUserId(userId);
			
			JSONArray jsArr = new JSONArray();
			
			for (Favorites fav : favorites) {
				JSONObject obj = new JSONObject();
				
				obj.put("personalActivityId", fav.getPersonalActivityId());
				
				jsArr.add(obj);
			}
			
			request.setAttribute("jsArr", jsArr);
			
			super.goToPage("activity/favoriteList.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		super.doPost(request, response);

		int activityId = Integer.parseInt(request.getParameter("activityId"));
		String userId = request.getParameter("userId");
		int activityCheck = Integer.parseInt(request.getParameter("activityCheck"));
		// 0: 개인 1: 업주
		
		FavoritesDao dao = new FavoritesDao();

		boolean success = false;
		try {
			success = dao.insertFovoritesData(activityId, userId, activityCheck);
			
			if (success) {
				 response.setStatus(HttpServletResponse.SC_OK);
			}
			else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
