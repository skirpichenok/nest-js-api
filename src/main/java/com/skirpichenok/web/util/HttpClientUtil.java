package com.skirpichenok.web.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skirpichenok.web.bean.AuthDetails;

/**
 * HttpClientUtil contains methods for authentication.
 */
public final class HttpClientUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

	private HttpClientUtil() {
	}

	/**
	 * Method requests authorization code.
	 * 
	 * @param host
	 *            String
	 * @param clientId
	 *            String
	 * @return String
	 */
	public static String authorizationCode(String host, String clientId) {
		return "redirect:".concat(host).concat("/login/oauth2?client_id=").concat(clientId).concat("&state=STATE");
	}

	/**
	 * Method requests access_token and saves it in the current session as AuthDetails object.
	 *
	 * @param tokenHost
	 *            String
	 * @param fireBaseHost
	 *            String
	 * @param code
	 *            String
	 * @param clientId
	 *            String
	 * @param clientSecret
	 *            String
	 * @param grantType
	 *            String
	 */
	public static void accessToken(String tokenHost, String fireBaseHost, String code, String clientId,
			String clientSecret, String grantType) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost method = new HttpPost(
					tokenHost.concat("/oauth2/access_token?client_id=").concat(clientId).concat("&code=").concat(code)
							.concat("&client_secret=").concat(clientSecret).concat("&grant_type=").concat(grantType));
			CloseableHttpResponse response = client.execute(method);
			JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
			RequestUtil.getRequest().getSession().setAttribute("authDetails",
					new AuthDetails(fireBaseHost, jsonObject.getLong("expires_in"), clientId, grantType,
							jsonObject.getString("access_token"), clientSecret, code));
		} catch (Exception ex) {
			LOGGER.error("Error has occurred.", ex);
		}
	}

	/**
	 * Method requests existing devices and puts it in the current request info about the 'Smoke+CO Alarm' device if
	 * it's present in the current configuration on the server.
	 *
	 * @param apiHost
	 *            String
	 */
	public static void devices(String apiHost) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet method = new HttpGet(
					apiHost.concat("/devices?auth=").concat(RequestUtil.getAuthDetails().getAccessToken()));
			CloseableHttpResponse response = client.execute(method);
			JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
			if (jsonObject.getJSONObject("smoke_co_alarms") != null) {
				JSONObject smokeCoAlarmJsonData = jsonObject.getJSONObject("smoke_co_alarms");
				String key = (String) smokeCoAlarmJsonData.keys().next();
				RequestUtil.getRequest().setAttribute("smokeCoAlarmJsonData", smokeCoAlarmJsonData.getJSONObject(key));
			}
		} catch (Exception ex) {
			LOGGER.error("Error has occurred.", ex);
		}
	}
}
