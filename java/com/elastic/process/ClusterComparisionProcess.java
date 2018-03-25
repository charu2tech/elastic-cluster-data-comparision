package com.elastic.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.api.response.validation.ElasticApiValidater;
import com.elastic.http.HttpClient;
import com.elastic.http.HttpRequest;
import com.elastic.pojo.Api;
import com.elastic.pojo.ApiHandlerMapper;
import com.elastic.pojo.ApiType;
import com.elastic.pojo.ApiValidationMessages;
import com.elastic.pojo.ClusterDetails;
import com.elastic.pojo.ResponseEntity;
import com.elastic.response.handler.ResponseHandler;
import com.elastic.util.FileUtil;
import com.elastic.util.JsonUtil;
import com.elastic.util.StringUtil;

public class ClusterComparisionProcess implements ElasticProcess {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClusterComparisionProcess.class);

	@Override
	public boolean process() {
		ClusterComparisionProcess process = new ClusterComparisionProcess();
		ClusterDetails clusterDetails = process.getClusterDetails();
		List<ApiHandlerMapper> apis = process.getApiHandlerMapper();
		Boolean isValidated= process.doValidate(clusterDetails, apis);
		HttpClient.closeHttpClient();
		return isValidated;
	}

	private boolean doValidate(ClusterDetails clusterDetails, List<ApiHandlerMapper> apis) {
		Api api = null;
		ApiValidationMessages apiValidationMessages=null;
		ResponseHandler responseHandler = null;
		ResponseEntity sourceResponseEntity = null;
		ResponseEntity destinationResponseEntity = null;
		ElasticApiValidater validater = null;

		for (ApiHandlerMapper apiMapper : apis) {
			try {
				responseHandler = (ResponseHandler) Class.forName(apiMapper.getResponseHandlerClass()).newInstance();
				validater = (ElasticApiValidater) Class.forName(apiMapper.getValidaterClass()).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException exception) {
				LOGGER.error("Unable to create instances for validater or responsehandlers.", exception);
				return false;
			}

			api = apiMapper.getApi();
			apiValidationMessages=apiMapper.getApiValidationMessages();
			sourceResponseEntity = new ResponseEntity();
			LOGGER.info(apiValidationMessages.getStartMessage());
			sourceResponseEntity
					.buildElasticApiUrl(StringUtil.buildUrl(clusterDetails.getSourceClusterLbUrl(), api.getApiPath()))
					.buildData(HttpRequest.executeGet(sourceResponseEntity.getElasticApiUrl(), responseHandler));

			destinationResponseEntity = new ResponseEntity();
			destinationResponseEntity
					.buildElasticApiUrl(
							StringUtil.buildUrl(clusterDetails.getDestinationClusterLbUrl(), api.getApiPath()))
					.buildData(HttpRequest.executeGet(destinationResponseEntity.getElasticApiUrl(), responseHandler));
			if(sourceResponseEntity.getData()==null || destinationResponseEntity.getData()==null || 
					!validater.validate(Arrays.asList(sourceResponseEntity, destinationResponseEntity))){
				LOGGER.error(apiValidationMessages.getFailureMessage());
				return false;
			}else{
				LOGGER.info(apiValidationMessages.getSuccessMessage());
			}

		}
		return true;
	}

	private Map<ApiType, String> getApiJsonFiles() {
		Map<ApiType, String> apiFilesMap = new HashMap<>();
		apiFilesMap.put(ApiType.PING, "F:\\Rt\\Study\\WS\\demo\\data1\\resources\\elastic_ping_apis.json");
		return apiFilesMap;
	}

	private List<ApiHandlerMapper> getApiHandlerMapper() {
		List<ApiHandlerMapper> apiHandlerMapperList = new ArrayList<>();
		Map<ApiType, String> apiJsonFiles = getApiJsonFiles();
		apiJsonFiles.forEach((k, v) -> {
			apiHandlerMapperList.addAll(JsonUtil.readJsonAndConvertToObject(v, ApiHandlerMapper.class));
		});
		return apiHandlerMapperList;
	}

	private ClusterDetails getClusterDetails() {
		Properties properties = FileUtil.getProperties("F:\\Rt\\Study\\WS\\demo\\data1\\resources\\config.properties");
		String sourceClusterUrl = properties.getProperty("elastic.source.cluster.url");
		String destinationClusterUrl = properties.getProperty("elastic.destination.cluster.url");
		ClusterDetails clusterDetails = new ClusterDetails();
		clusterDetails.setDestinationClusterLbUrl(destinationClusterUrl);
		clusterDetails.setSourceClusterLbUrl(sourceClusterUrl);
		return clusterDetails;

	}

}
