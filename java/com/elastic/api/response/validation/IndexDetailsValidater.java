package com.elastic.api.response.validation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.constants.Elastic;
import com.elastic.pojo.IndexDetailsPojo;
import com.elastic.pojo.ResponseEntity;
import com.elastic.util.Cache;
import com.elastic.util.JsonConverter;

public class IndexDetailsValidater implements ElasticApiValidater{
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexDetailsValidater.class);

	@Override
	public Boolean validate(ResponseEntity sourceCluster, ResponseEntity destinationCluster) {
		 Map<String,IndexDetailsPojo> allSourceIndexDetailsList=getAllIndexDetailsList(sourceCluster.getResponseJsonArray(),true);
		 Map<String,IndexDetailsPojo> allDestinationIndexDetailsList=getAllIndexDetailsList(destinationCluster.getResponseJsonArray(),false);
		 Boolean result=false;
		 if(allSourceIndexDetailsList.size()!=allDestinationIndexDetailsList.size()){
			 LOGGER.error("Source and destination cluster Indexes Mismatched.");
			 LOGGER.error("Source Index Names={}",allSourceIndexDetailsList.keySet());
			 LOGGER.error("Destination Index Names={}",allDestinationIndexDetailsList.keySet());
			 return result;
		 }
		 for (Map.Entry<String, IndexDetailsPojo> entry : allSourceIndexDetailsList.entrySet())
		 {
			 IndexDetailsPojo sourceIndex=entry.getValue();
			 IndexDetailsPojo destinationIndex=allDestinationIndexDetailsList.get(entry.getKey());
			 if(!Elastic.JSON_VALUE_OPEN.equals(sourceIndex.getStatus())){
				 LOGGER.error("Source cluster index status must be open. current status={}, url={}",sourceIndex.getStatus(),sourceCluster.getElasticApiUrl());
				 return result;
			 }
			 if(!Elastic.JSON_VALUE_OPEN.equals(destinationIndex.getStatus())){
				 LOGGER.error("Destination cluster index status must be open. current status={}, url={}",destinationIndex.getStatus(),destinationCluster.getElasticApiUrl());
				 return result;
			 }
			 if(!sourceIndex.getDocsCount().equals(destinationIndex.getDocsCount())){
				 LOGGER.error("Index document Count is mismatched. index name={}",sourceIndex.getIndex());
				 LOGGER.error("Source cluster details. url={} Document count={}",sourceCluster.getElasticApiUrl(),sourceIndex.getDocsCount());
				 LOGGER.error("Destination cluster details. url={} Document count={}",destinationCluster.getElasticApiUrl(),destinationIndex.getDocsCount());
			 }
			 if(!sourceIndex.getPri().equals(destinationIndex.getPri())){
				 LOGGER.error("Index primary shards are mismatched. index name={}",sourceIndex.getIndex());
				 LOGGER.error("Source cluster details. url={} primary shards={}",sourceCluster.getElasticApiUrl(),sourceIndex.getPri());
				 LOGGER.error("Destination cluster details. url={} primary shards={}",destinationCluster.getElasticApiUrl(),destinationIndex.getPri());
			 }
			 if(!sourceIndex.getRep().equals(destinationIndex.getRep())){
				 LOGGER.error("Index replica shards are mismatched. index name={}",sourceIndex.getIndex());
				 LOGGER.error("Source cluster details. url={}  replica shards={}",sourceCluster.getElasticApiUrl(),sourceIndex.getRep());
				 LOGGER.error("Destination cluster details. url={}  replica shards={}",destinationCluster.getElasticApiUrl(),destinationIndex.getRep());
			 }
		 }
		 result=true;	
		return result;
	}
	
	private Map<String,IndexDetailsPojo> getAllIndexDetailsList(JSONArray responseData, Boolean cacheIndexName){
		List<IndexDetailsPojo> indexDetailsPojoList=JsonConverter.toList(responseData, IndexDetailsPojo.class);
		Map<String, IndexDetailsPojo> indexListMap=new HashMap<>();
		for(IndexDetailsPojo indicesListPojo: indexDetailsPojoList){
			if(true){
				Cache.INDEX_TYPE_MAP_CACHE.put(indicesListPojo.getIndex(), Collections.EMPTY_LIST);
			}
			indexListMap.put(indicesListPojo.getIndex(), indicesListPojo);
		}
		
		return indexListMap;
	}
	
}
