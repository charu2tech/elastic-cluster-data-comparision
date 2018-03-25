package com.elastic.util;

import org.apache.http.Header;

import com.elastic.constants.HttpConstants;

public class HttpUtil {
	
	public static String getEncoding(Header contentEncoding){
		String encodingFormat=null;
		if(contentEncoding!=null){
			encodingFormat=contentEncoding.getValue();
		}
		if(encodingFormat==null){
			encodingFormat=HttpConstants.UTF8.getName();
		}
		return encodingFormat;		
	}
}
