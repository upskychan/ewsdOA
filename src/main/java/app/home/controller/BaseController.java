package app.home.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

	public String sysName = "易网时代OA系统";

	// 获取基于应用程序的url绝对路径
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		return request.getContextPath() + url;
	}

	// 根据url获取当前控制器名称
	public String[] controllerName(HttpServletRequest request) {
		// HttpServletRequest request = null;
		if (null != request) {
			String url = request.getRequestURI();
			String[] urlArr = url.split("/");
			return urlArr;
		} else {
			return null;
		}
	}

	// 根据url获取当前控制器名称
	public String controllerUrl(HttpServletRequest request) {
		// HttpServletRequest request = null;
		String controllerUrl = "";
		if (null != request) {
			controllerUrl =  request.getRequestURI();
		}
		return controllerUrl;
	}

	// 根据url获取当前操作名称
	public String actionName(String url) {
		return url;
	}

}
