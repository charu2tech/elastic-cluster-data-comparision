package com.elastic.api.response.validation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.pojo.ResponseEntity;

public class PingClusterValidater implements ElasticApiValidater<Integer> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PingClusterValidater.class);
	
	@Override
	public Boolean validate(List<ResponseEntity<Integer>> responseEntityList) {
		for(ResponseEntity<Integer> responseEntity: responseEntityList){
			if(responseEntity.getData()!=200){
				LOGGER.info("Cluster is not reachable. url={}",responseEntity.getElasticApiUrl());
				return false;
			}
		}
		return true;
	}
}
