package com.skirpichenok.web.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.skirpichenok.web.bean.AuthDetails;

/**
 * RequestUtil helps to get the HttpServletRequest.
 */
public final class RequestUtil {

	private RequestUtil() {
	}

	/**
	 * Method returns HttpServletRequest for current thread.
	 * 
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	/**
	 * Method removes authDetails from current session.
	 */
	public static void reset() {
		getRequest().getSession().removeAttribute("authDetails");
	}

	/**
	 * Method returns AuthDetails for current session.
	 *
	 * @return AuthDetails
	 */
	public static AuthDetails getAuthDetails() {
		return (AuthDetails) getRequest().getSession().getAttribute("authDetails");
	}

	/**
	 * Method returns true if authentication is already passed for current session.
	 *
	 * @return boolean
	 */
	public static boolean isAuthorized() {
		return getAuthDetails() != null;
	}
}
