package com.elastic.util;

import java.util.StringJoiner;

import com.elastic.constants.Elastic;

public class StringUtil {
	
	public static String trimIfNotNull(String data){
		if(data!=null){
			data=data.trim();
		}
		return data;
	}
	
	public static String getUrl(String lbUrl, String apiPath){
		lbUrl=removeIfEndsWith(lbUrl,Elastic.URI_SEPERATOR.getName());
		StringJoiner url = new StringJoiner(Elastic.URI_SEPERATOR.getName());
		url.add(lbUrl);
		url.add(apiPath);
		return url.toString();
	}
	
	public static String removeIfEndsWith(String url, String suffix){
		if(url.endsWith(suffix)){
			url=url.substring(0, url.length()-suffix.length());
		}
		return url;
	}
	
	public static String getElasticJsonFormatQueryParams(){
		StringBuilder jsonFormatQueryParams=new StringBuilder();
		jsonFormatQueryParams.append(Elastic.QUERY_PARAM_SEPERATOR.getName())
							 .append(Elastic.QUERY_PARAM_JSON_FORMATER.getName())
							 .append(Elastic.QUERY_PARAM_SEPERATOR.getName())
							 .append(Elastic.QUERY_PARAM_PRETTY.getName());
		return jsonFormatQueryParams.toString();		
	}

}
