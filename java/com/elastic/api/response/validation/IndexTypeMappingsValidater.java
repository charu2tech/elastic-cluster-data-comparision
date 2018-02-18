package com.elastic.api.response.validation;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.pojo.ResponseEntity;

public class IndexTypeMappingsValidater implements ElasticApiValidater{
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexTypeMappingsValidater.class);

	@Override
	public Boolean validate(ResponseEntity sourceCluster, ResponseEntity destinationCluster) {
		JSONArray sourceClusterResponseJsonArray=sourceCluster.getResponseJsonArray();
		JSONArray destinationClusterResponseJsonArray=destinationCluster.getResponseJsonArray();
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
