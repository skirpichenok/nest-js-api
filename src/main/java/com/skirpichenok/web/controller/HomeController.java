package com.skirpichenok.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skirpichenok.web.util.HttpClientUtil;
import com.skirpichenok.web.util.RequestUtil;

/**
 * HomeController.
 */
@Controller
public class HomeController extends BaseController {

	/**
	 * Handles the '/' and '/home' requests.
	 * 
	 * @return String
	 */
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home() {
		return RequestUtil.isAuthorized() ? "redirect:dashboard" : "home";
	}

	/**
	 * Handles the '/authorizationCode' request.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/authorizationCode", method = RequestMethod.POST)
	public String authorizationCode() {
		return HttpClientUtil.authorizationCode(getAuthHost(), getClientId());
	}

	/**
	 * Handles the '/reset' request.
	 * 
	 * @return String
	 */
	@RequestMapping(value = {"/reset"}, method = RequestMethod.GET)
	public String reset() {
		RequestUtil.reset();
		return "redirect:home";
	}

	/**
	 * Handles the '/callback' request.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback(@RequestParam("code") String code) throws Exception {
		HttpClientUtil.accessToken(getTokenHost(), getFireBaseHost(), code, getClientId(), getClientSecret(),
				getGrantType());
		return RequestUtil.isAuthorized() ? "redirect:dashboard" : "home";
	}
}