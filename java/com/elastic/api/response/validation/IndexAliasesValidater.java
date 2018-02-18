package com.elastic.api.response.validation;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.pojo.IndexAliasPojo;
import com.elastic.pojo.ResponseEntity;
import com.elastic.util.JsonConverter;

public class IndexAliasesValidater implements ElasticApiValidater{
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexAliasesValidater.class);

	@Override
	public Boolean validate(ResponseEntity sourceCluster, ResponseEntity destinationCluster) {
		 Map<String,List<String>> allSourceIndexAliasList=getAllIndexAliasesList(sourceCluster.getResponseJsonArray());
		 Map<String,List<String>> allDestinationIndexDetailsList=getAllIndexAliasesList(destinationCluster.getResponseJsonArray());
		 Boolean result=false;
		 if(allSourceIndexAliasList.size()!=allDestinationIndexDetailsList.size()){
			 LOGGER.error("Source and destination cluster Index aliases are mismatched.");
			 LOGGER.error("Source Index and alias details",allSourceIndexAliasList);
			 LOGGER.error("Destination Index and alias details",allDestinationIndexDetailsList);
			 return result;
		 }else if(allSourceIndexAliasList.size()==0){
			 LOGGER.error("Source and destination cluster Index aliases are matched.");
			 result=true;
			 return result;
		 }
		 for (Map.Entry<String, List<String>> entry : allSourceIndexAliasList.entrySet())
		 {
			 List<String> sourceIndexAliasList=entry.getValue();
			 List<String> destinationIndexAliasList=allDestinationIndexDetailsList.get(entry.getKey());
			 Collections.sort(sourceIndexAliasList);
			 Collections.sort(destinationIndexAliasList);
			 if(sourceIndexAliasList.equals(destinationIndexAliasList)){
				 result=true;
			 }else{
				 LOGGER.error("Source and destination index aliases are mismatched");
				 LOGGER.error("Source Index={}, alias={}",entry.getKey(),sourceIndexAliasList);
				 LOGGER.error("Destination Index={}, alias={}",entry.getKey(),destinationIndexAliasList); 
				 return false;
			 }
		 }
			 return result;
	}
	
	private Map<String,List<String>> getAllIndexAliasesList(JSONArray responseData){
		List<IndexAliasPojo> indexAliasPojoList=JsonConverter.toList(responseData, IndexAliasPojo.class);
		Map<String,List<String>> indexAliasList=new HashMap<>();
		for(IndexAliasPojo indexAliasPojo: indexAliasPojoList){
			if(indexAliasList.containsKey(indexAliasPojo.getIndex())){
				List<String> aliases=indexAliasList.get(indexAliasPojo.getIndex());
				aliases.add(indexAliasPojo.getAlias());
				indexAliasList.put(indexAliasPojo.getIndex(),aliases);
			}else{
				indexAliasList.put(indexAliasPojo.getIndex(),Arrays.asList(indexAliasPojo.getAlias()));
			}
		}
		
		return indexAliasList;
	}
	
	
}
