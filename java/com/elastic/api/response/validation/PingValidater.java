package com.elastic.api.response.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.elastic.pojo.ResponseEntity;

public class PingValidater implements ElasticApiValidater {
	private static final Logger LOGGER = LoggerFactory.getLogger(PingValidater.class);

	@Override
	public Boolean validate(ResponseEntity sourceCluster, ResponseEntity destinationCluster) {
		LOGGER.info("Validating whether both the clusters are reachable or not");
		Boolean isPingValidated=false;
		isPingValidated=this.validatePing(sourceCluster.getElasticApiUrl(),sourceCluster.getStatusCode());
		if(isPingValidated){
			isPingValidated=this.validatePing(destinationCluster.getElasticApiUrl(),destinationCluster.getStatusCode());
		}
		if(isPingValidated){
			LOGGER.info("Source and destination clusters are reachable.");
		}else{
			LOGGER.error("Validation failed.Either source or destination cluster is not reachable.");
		}
		return isPingValidated;
	}
	
	private Boolean validatePing(String elasticApiUrl, int statusCode){
		LOGGER.info("Trying to ping cluster. url=",elasticApiUrl);
		if(statusCode==200){
			LOGGER.info("Cluster is reachable. url=",elasticApiUrl);
			return true;
		}else{
			LOGGER.error("Cluster is not reachable. url=",elasticApiUrl);
			return false;
		}		
	}	
}
