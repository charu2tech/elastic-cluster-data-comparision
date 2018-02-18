package com.elastic.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
	public static final Map<String, List<String>> INDEX_TYPE_MAP_CACHE=new ConcurrentHashMap<>();

}
