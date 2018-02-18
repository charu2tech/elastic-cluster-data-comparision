package com.elastic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.elastic.constants.ElasticApis;
import com.elastic.http.HttpClient;
import com.elastic.http.HttpClientUtil;
import com.elastic.response.handler.JsonResponseHandler;
import com.elastic.response.handler.ResponseHandler;
import com.elastic.response.handler.StringResponseHandler;
import com.elastic.util.StringUtil;

public class Test {
	
	private final ResponseHandler<JSONArray> jsonResponseHandler;
	private final ResponseHandler<String> stringResponseHandler;
	private final HttpClient httpClient;
	private final HttpClientUtil httpClientUtil;
	
	
	public Test(){
		this.jsonResponseHandler = (ResponseHandler<JSONArray>) new JsonResponseHandler();
		this.stringResponseHandler = (ResponseHandler<String>) new StringResponseHandler();
		this.httpClient=new HttpClient();
		this.httpClientUtil=new HttpClientUtil(httpClient);
	}
	

	public static void main(String[] args) {
		Test test = new Test();
		test.doProcess();
		test.httpClient.closeClient();
	}
	
	public  void doProcess(){
		String lbUrl="http://localhost:9200/";		
		String url=StringUtil.getUrl(lbUrl, ElasticApis.CLUSTER_HEALTH.getPath());	
		JSONArray jsonArray=(JSONArray)httpClientUtil.executeGet(url, jsonResponseHandler);
		JSONObject jsonObject=(JSONObject)jsonArray.get(0);		
		System.out.println(jsonObject.get("status"));
	}
}
