package com.skirpichenok.web.bean;

/**
 * AuthDetails.
 */
public class AuthDetails {

	private long expiresIn;
	private String clientId;
	private String grantType;
	private String accessToken;
	private String clientSecret;
	private String fireBaseHost;
	private String authorizationCode;

	/**
	 * Constructor.
	 * 
	 * @param fireBaseHost
	 *            String
	 * @param expiresIn
	 *            long
	 * @param clientId
	 *            String
	 * @param grantType
	 *            String
	 * @param accessToken
	 *            String
	 * @param clientSecret
	 *            String
	 * @param authorizationCode
	 *            String
	 */
	public AuthDetails(String fireBaseHost, long expiresIn, String clientId, String grantType, String accessToken,
			String clientSecret, String authorizationCode) {
		this.clientId = clientId;
		this.expiresIn = expiresIn;
		this.grantType = grantType;
		this.accessToken = accessToken;
		this.clientSecret = clientSecret;
		this.fireBaseHost = fireBaseHost;
		this.authorizationCode = authorizationCode;
	}

	public String getFireBaseHost() {
		return fireBaseHost;
	}

	public void setFireBaseHost(String fireBaseHost) {
		this.fireBaseHost = fireBaseHost;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
}
