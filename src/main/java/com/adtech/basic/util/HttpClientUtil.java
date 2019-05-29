package com.adtech.basic.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * ClassName: HttpClientUtil模拟提交post和get
 * 
 * @Description: webServices工具类
 */
public class HttpClientUtil {

	/**
	 * post 请求 有参数的POST
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String dopost(String url, Map<String, String> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));// 设置参数
		}

		CloseableHttpResponse response = null;
		String result = "";
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");// 返回数据信息
			} else {
				result = "{message:返回数据失败}";
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
			result = "{message:" + e.getMessage() + "}";
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
					result = "{message:" + e.getMessage() + "}";
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
					result = "{message:" + e.getMessage() + "}";
				}
			}
		}
		return result;
	}

	/**
	 * post 参数为JSON
	 * 
	 * @param url
	 * @param jsonParams
	 * @return
	 */
	public static String doPostForJson(String url, String jsonParams) {
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(jsonParams, ContentType.create(
					"application/json", "utf-8")));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");// 返回数据信息
			} else {
				result = "{message:返回数据失败}";
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
			result = "{message:" + e.getMessage() + "}";
		} finally {
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
					result = "{message:" + e.getMessage() + "}";
				}
			}
		}
		return result;
	}

	/**
	 * get
	 * 
	 * @param url
	 * @param jsonParams
	 * @return
	 * 
	 */
	public static String doget(String url) {
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		String srtResult = "";

		try {
			HttpResponse httpResponse = httpCilent.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				srtResult = EntityUtils.toString(httpResponse.getEntity());// 返回的数据信息
			} else if (httpResponse.getStatusLine().getStatusCode() == 400) {
				srtResult = "{message:响应码为400失败}";
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				srtResult = "{message:响应码为500失败}";
			}
		} catch (IOException e) {
			e.printStackTrace();
			srtResult = "{message:" + e.getMessage() + "}";
		} finally {
			try {
				httpCilent.close();
			} catch (IOException e) {
				e.printStackTrace();
				srtResult = "{message:" + e.getMessage() + "}";
			}
		}
		return srtResult;
	}

	public static void main(String[] args) throws Exception {
		// ==========================post测试代码开始==========================
		String ulr = "http://192.168.5.150:8080/adrmp/restFul/zdUnzipResult";
		Map<String, String> map = new HashMap<String, String>();
		map.put("StudyInstanceUID", "001.zip");
		map.put("ftpStatus", "ftp200");
		String result = dopost(ulr, map);
		System.out.println("========" + result);
		JSONObject jsonObject = new JSONObject(result);
		System.out.println("message======" + jsonObject.get("message"));
		System.out.println("result======" + jsonObject.get("result"));
		// ============================post测试代码结束=======================
		// =========================get测试代码开始=========================
		// String ulr =
		// "http://192.168.5.150:8080/adrmp/restFul/zdUnzipResult?StudyInstanceUID=001.zip&ftpStatus=ftp200";
		// String result = doget(ulr);
		// System.out.println(result);
		// JSONObject jsonObject = new JSONObject(result);
		// System.out.println("message======" + jsonObject.get("message"));
		// System.out.println("result======" + jsonObject.get("result"));
		// =========================get测试代码结束===========

	}

}
