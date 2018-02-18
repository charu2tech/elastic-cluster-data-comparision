package com.elastic.api.response.validation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.constants.Elastic;
import com.elastic.pojo.ResponseEntity;
import com.elastic.util.JsonUtil;

public class ClusterHealthValidater implements ElasticApiValidater{
	private static final Logger LOGGER = LoggerFactory.getLogger(ClusterHealthValidater.class);

	@Override
	public Boolean validate(ResponseEntity sourceCluster, ResponseEntity destinationCluster) {
		LOGGER.info("Validating whether both the clusters health is green or yellow");
		Boolean isClusterHealthValidated=false;
		isClusterHealthValidated=this.validateClusterHealth(sourceCluster.getElasticApiUrl(),sourceCluster.getResponseJsonArray());
		if(isClusterHealthValidated){
			isClusterHealthValidated=this.validateClusterHealth(destinationCluster.getElasticApiUrl(),destinationCluster.getResponseJsonArray());
		}
		if(isClusterHealthValidated){
			LOGGER.info("Source and destination clusters health is validated.");
		}else{
			LOGGER.error("Validation failed.Either source or destination cluster health is not in required status(green or yellow)");
		}
		return isClusterHealthValidated;
	}
	
	private Boolean validateClusterHealth(String elasticApiUrl, JSONArray responseJsonArray){
		LOGGER.info("Validating cluster health. Url={}, responseJsonArray={}",elasticApiUrl,responseJsonArray);
		Boolean result=false;
		JSONObject jsonObject=JsonUtil.getFirstElementFromArray(responseJsonArray);
		if(jsonObject!=null){
		String status=(String)jsonObject.get(Elastic.JSON_KEY_STATUS.getName());
		if(status!=null){
		if(Elastic.JSON_VALUE_YELLOW.getName().equalsIgnoreCase(status) || Elastic.JSON_VALUE_GREEN.getName().equalsIgnoreCase(status)){
			result=true;			
		}else if(Elastic.JSON_VALUE_RED.getName().equalsIgnoreCase(status)){
			LOGGER.error("Cluster health validation is failed. url={}, status={}",elasticApiUrl,status);
		}
		}	
		}
		return result;
	}

	
}
