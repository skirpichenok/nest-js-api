package com.skirpichenok.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skirpichenok.web.util.HttpClientUtil;
import com.skirpichenok.web.util.RequestUtil;

/**
 * DashboardController.
 */
@Controller
public class DashboardController extends BaseController {

	/**
	 * Handles the '/dashboard' request.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		String page;
		if (RequestUtil.isAuthorized()) {
			HttpClientUtil.devices(getApiHost());
			page = "dashboard";
		} else {
			page = "redirect:home";
		}
		return page;
	}
}