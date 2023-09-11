package com.FunFinder360.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SuperController {
	public void doGet(HttpServletRequest request, HttpServletResponse response);
	public void doPost(HttpServletRequest request, HttpServletResponse response);
}
