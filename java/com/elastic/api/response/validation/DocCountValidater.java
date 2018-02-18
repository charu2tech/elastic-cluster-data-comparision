package com.elastic.api.response.validation;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.constants.Elastic;
import com.elastic.pojo.ResponseEntity;
import com.elastic.util.JsonUtil;

public class DocCountValidater implements ElasticApiValidater{
	private static final Logger LOGGER = LoggerFactory.getLogger(DocCountValidater.class);

	@Override
	public Boolean validate(ResponseEntity sourceCluster, ResponseEntity destinationCluster)  {
		LOGGER.info("Validating document count for both the clusters");
		JSONArray sourceClusterResponseJsonArray=sourceCluster.getResponseJsonArray();
		JSONArray destinationClusterResponseJsonArray=destinationCluster.getResponseJsonArray();
		Object sourceClusterDocumentCount=null;
		Object destinationClusterDocumentCount=null;
		Boolean isDocCountValidated=false;
		JSONObject jsonObject=JsonUtil.getFirstElementFromArray(sourceClusterResponseJsonArray);
		if(jsonObject!=null){
			sourceClusterDocumentCount=jsonObject.get(Elastic.JSON_KEY_COUNT.getName());
			jsonObject=JsonUtil.getFirstElementFromArray(destinationClusterResponseJsonArray);
			if(jsonObject!=null){
				destinationClusterDocumentCount=jsonObject.get(Elastic.JSON_KEY_COUNT.getName());
				if(sourceClusterDocumentCount!=null && destinationClusterDocumentCount!=null){
					 if(((String)sourceClusterDocumentCount).equals(((String)destinationClusterDocumentCount))){
						 isDocCountValidated=true;
					 }
				 }
			}else{
				 LOGGER.error("Destination cluster response is null. url=",destinationCluster.getElasticApiUrl());
		}
		}else{
				 LOGGER.error("Source cluster response is null. url=",sourceCluster.getElasticApiUrl());
		}
		 if(isDocCountValidated){
			 LOGGER.info("Source and destination cluster document count is matched");
		}else{
			 LOGGER.error("Source and destination cluster document count is mismatched.");
			 LOGGER.error("Source cluster url={}, document count={}",sourceCluster.getElasticApiUrl(),sourceClusterDocumentCount);
			 LOGGER.error("Destination cluster url={}, document count={}",destinationCluster.getElasticApiUrl(),destinationClusterDocumentCount); 
		 }
		return isDocCountValidated;
	}	
}
