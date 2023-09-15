package com.FunFinder360.Servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FunFinder360.Controller.SuperController;
import com.oreilly.servlet.MultipartRequest;

import Utility.Utility;

@WebServlet(urlPatterns = { "/FunFinder360" }, initParams = {
		@WebInitParam(name = "routersTxt", value = "/WEB-INF/routes.txt"),
		@WebInitParam(name = "settingTxt", value = "/WEB-INF/setting.txt"),
		@WebInitParam(name = "companyInfoTex", value = "/WEB-INF/company_info.txt") })

public class MainServlet extends HttpServlet {
	private Map<String, SuperController> routersMap = null;
	private Map<String, String> settingMap = null;
	private Map<String, String> companyInfoMap = null;

	private String imageUploadWebPath = null;
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init start");
		ServletContext application = config.getServletContext();

		String routersFile = config.getInitParameter("routersTxt");
		String routersPathName = application.getRealPath(routersFile);
		routersMap = Utility.getRouterData(routersPathName);

		String settingFile = config.getInitParameter("settingTxt");
		String settingPathName = application.getRealPath(settingFile);
		settingMap = Utility.getSettingMap(settingPathName);

		application.setAttribute("settingMap", this.settingMap);

		String companyInfoFile = config.getInitParameter("companyInfoTex");
		String companyInfoPathName = application.getRealPath(companyInfoFile);
		companyInfoMap = Utility.getCompanyInfoData(companyInfoPathName);

		application.setAttribute("companyInfoMap", this.companyInfoMap);
		
		String tempPath = settingMap.get("uploadPath");
		if (tempPath == null) {
			tempPath = "image";
		}
		
		this.imageUploadWebPath = application.getRealPath(tempPath);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getParameter("command");

		System.out.println("command : " + command);

		if (command == null) {
			System.out.println("command is null");
			MultipartRequest mr = Utility.getMultipartRequest(request, this.imageUploadWebPath);
			if (mr != null) {
				command = mr.getParameter("command");
				request.setAttribute("mr", mr);
			} else {
				System.out.println("multpartRequest object is null");
			}
		}
		SuperController controller = routersMap.get(command);
		if (controller == null) {
			System.out.println("controller is null");
		} else {
			String method = request.getMethod();
			if (method.equalsIgnoreCase("get")) {
				controller.doGet(request, response);
			} else {
				controller.doPost(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doProcess(request, response);
	}

}
