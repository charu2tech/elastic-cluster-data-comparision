package com.elastic.api.response.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.pojo.ResponseEntity;

public class IndexTypeExistValidater implements ElasticApiValidater {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexTypeExistValidater.class);
	@Override
	public Boolean validate(ResponseEntity sourceCluster, ResponseEntity destinationCluster) {
		LOGGER.info("Validating whether index type exist or not. url={}",sourceCluster.getElasticApiUrl());
		if(sourceCluster.getStatusCode()==200){
			LOGGER.info("Index type exist. url={}",sourceCluster.getElasticApiUrl());
			return true;
		}else{
			LOGGER.error("Index type does not exist. url={}",sourceCluster.getElasticApiUrl());
			return false;
		}	
	}	
}
