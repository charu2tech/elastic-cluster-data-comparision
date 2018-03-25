package com.elastic.util;

import com.elastic.constants.HttpConstants;

public class StringUtil {
	
	public static boolean isNull(String data){
		return data==null? true : false;
	}
	
	public static boolean isEmpty(String data){
		return data.trim().isEmpty();
	}
	
	public static boolean isNullOrEmpty(String data){
		return isNull(data)? true :isEmpty(data);
	}
	
	
	public static String trimIfNotNullOrEmpty(String data){
		if(!isNullOrEmpty(data)){
			data=data.trim();
		}
		return data;
	}	
	
	public static String buildUrl(String hostUrl, String apiPath){
		StringBuilder url=new StringBuilder();
		url.append(HttpConstants.HTTP.getName());
		url.append(HttpConstants.PROTOCOL_URL_SEPERATOR.getName());
		url.append(removeIfEndsWith(hostUrl,HttpConstants.URI_SEPERATOR.getName()));
		if(!isNullOrEmpty(apiPath)){
		url.append(HttpConstants.URI_SEPERATOR.getName());
		url.append(removeIfEndsWith(apiPath,HttpConstants.URI_SEPERATOR.getName()));
		}		
		return url.toString();
	}
	
	public static String removeIfEndsWith(String url, String suffix){
		if(url.endsWith(suffix)){
			url=url.substring(0, url.length()-suffix.length());
		}
		return url;
	}
}
