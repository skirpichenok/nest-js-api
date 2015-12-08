package com.skirpichenok.web.controller;

import org.springframework.beans.factory.annotation.Value;

/**
 * BaseController.
 */
public class BaseController {

	@Value("${auth.host}")
	private String authHost;

	@Value("${token.host}")
	private String tokenHost;

	@Value("${firebase.host}")
	private String fireBaseHost;

	@Value("${api.host}")
	private String apiHost;

	@Value("${clientId}")
	private String clientId;

	@Value("${grantType}")
	private String grantType;

	@Value("${clientSecret}")
	private String clientSecret;

	public String getAuthHost() {
		return authHost;
	}

	public String getTokenHost() {
		return tokenHost;
	}

	public String getFireBaseHost() {
		return fireBaseHost;
	}

	public String getApiHost() {
		return apiHost;
	}

	public String getClientId() {
		return clientId;
	}

	public String getGrantType() {
		return grantType;
	}

	public String getClientSecret() {
		return clientSecret;
	}
}
