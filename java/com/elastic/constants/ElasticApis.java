package com.elastic.constants;

public enum ElasticApis {

	// http://localhost:9200/_cat/health?h=status,node.total,node.data&format=json&pretty
	// stop if status is red. allows if status is green or yellow
	// node.total, node.data should be greater than 1
	CLUSTER_HEALTH(HttpVerb.GET.name(), "_cat/health?h=status,node.total,node.data&format=json&pretty"),
	// http://localhost:9200/_cat/count?h=count&format=json&pretty
	CLUSTER_DOC_COUNT(HttpVerb.GET.name(), "_cat/count?h=count&format=json&pretty"),
	// http://localhost:9200/_cat/indices?h=pri,rep,status,index,docs.count&format=json&pretty
	INDEX_DETAILS(HttpVerb.GET.name(), "_cat/indices?h=pri,rep,status,index,docs.count&format=json&pretty"),
	// http://localhost:9200/posts?format=json&pretty
	// status 200 means index exist
	INDEX_EXIST(HttpVerb.HEAD.name(), "%s?format=json&pretty"),
	// http://localhost:9200/posts/doc?format=json&pretty
	// status 200 means type exist
	INDEX_TYPE_EXIST(HttpVerb.HEAD.name(), "%s/%s?format=json&pretty"),
	// http://localhost:9200/posts/_settings?filter_path=-**.creation_date,-**.uuid,-**.version,-**.number_of_shards,-**.number_of_replicas,-**.provided_name&format=json&pretty
	INDEX_SETTINGS(HttpVerb.GET.name(),
			"%s/_settings?filter_path=-**.creation_date,-**.uuid,-**.version&format=json&pretty"),
	// http://localhost:9200/posts/_mappings?format=json&pretty
	INDEX_MAPPINGS(HttpVerb.GET.name(), "%s/_mappings?format=json&pretty"),
	// http://localhost:9200/posts/_mappings/doc?format=json&pretty
	INDEX_TYPE_MAPPINGS(HttpVerb.GET.name(), "%s/_mapping/%s?format=json&pretty"),
	// http://localhost:9200/posts/_mapping/doc/field/message?format=json&pretty
	INDEX_TYPE_FIELD_MAPPINGS(HttpVerb.GET.name(), "%s/_mapping/%s/field/%s?format=json&pretty"),
	// http://localhost:9200/posts/_aliases?format=json&pretty
	INDEX_ALIASES(HttpVerb.GET.name(), "%s/_aliasess?format=json&pretty"),
	// http://localhost:9200/_cat/aliases?h=alias,index&format=json&pretty
	ALL_INDEX_ALIASES(HttpVerb.GET.name(), "_cat/aliases?h=alias,index&format=json&pretty"),

	// http://localhost:9200/_cat/repositories?format=json&pretty
	SNAPSHOT_REGISTERED_REPOSITORIES(HttpVerb.GET.name(), "_cat/repositories?format=json&pretty"),
	// http://localhost:9200/_cat/snapshots/repo1?format=json&pretty
	SNAPSHOTS(HttpVerb.GET.name(), "_cat/snapshots/%s?format=json&pretty");

	private String verb;
	private String path;

	private ElasticApis(String verb, String path) {
		this.verb = verb;
		this.path = path;
	}

	public String getVerb() {
		return verb;
	}

	public String getPath() {
		return path;
	}

}
