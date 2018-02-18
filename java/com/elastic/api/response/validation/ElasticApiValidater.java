package com.elastic.api.response.validation;

import com.elastic.pojo.ResponseEntity;

public interface ElasticApiValidater {
	
	public Boolean validate(final ResponseEntity sourceCluster, final ResponseEntity destinationCluster);

}
