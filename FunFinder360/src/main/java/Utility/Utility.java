package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.FunFinder360.Controller.SuperController;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Utility {
	public static Map<String, SuperController> getRouterData(String filePath) {
		Map<String, SuperController> map = new HashMap<String, SuperController>();
		Properties prop = getPropertiesData(filePath);
		Enumeration<Object> keys = prop.keys();

		while (keys.hasMoreElements()) {
			String command = keys.nextElement().toString();
			String className = prop.getProperty(command);

			try {
				Class<?> handleClass = Class.forName(className);
				SuperController instance = (SuperController) handleClass.newInstance();
				map.put(command, instance);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	public static Properties getPropertiesData(String filePath) {
		FileInputStream fs = null;
		Properties prop = null;
		try {
			// filePath 설정 필요
			fs = new FileInputStream(filePath);
			prop = new Properties();
			InputStreamReader reader = new InputStreamReader(fs, StandardCharsets.UTF_8);
			prop.load(reader);
		} catch (Exception e) {
			System.out.println("prop get error");
			e.printStackTrace();
		} finally {
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return prop;
	}

	public static MultipartRequest getMultipartRequest(HttpServletRequest request, String uploadPath) {
		// 이미지 업로드에 필요한 멀티 파트 객체 생성 -> 반환
		int maxPostSize = 10 * 1024 * 1024;
		String ENCODING = "UTF-8";
		MultipartRequest mr = null;
		System.out.println("file src : " +uploadPath);
		try {
			mr = new MultipartRequest(request, uploadPath, maxPostSize, ENCODING, new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mr;
	}

	public static Map<String, String> getSettingMap(String settingPathName) {
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = getPropertiesData(settingPathName);
		Enumeration<Object> keys = prop.keys();

		while (keys.hasMoreElements()) {
			String command = keys.nextElement().toString();
			String instance = prop.getProperty(command);

			try {
				map.put(command, instance);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static Map<String, String> getCompanyInfoData(String settingPathName) {
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = getPropertiesData(settingPathName);
		Enumeration<Object> keys = prop.keys();

		while (keys.hasMoreElements()) {
			String command = keys.nextElement().toString();
			String instance = prop.getProperty(command);

			try {
				map.put(command, instance);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
