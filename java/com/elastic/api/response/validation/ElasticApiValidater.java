package com.elastic.api.response.validation;

import java.util.List;

import com.elastic.pojo.ResponseEntity;

public interface ElasticApiValidater<E> {
	
	public Boolean validate(final List<ResponseEntity<E>> responseEntityList);

}
