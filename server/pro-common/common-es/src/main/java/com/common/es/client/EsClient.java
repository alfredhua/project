package com.common.es.client;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.Time;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.common.api.entity.request.PageRequest;
import com.common.es.anno.Document;
import com.common.es.entity.EsBaseEntity;
import com.common.es.entity.EsPageResponse;
import com.common.es.entity.EsScrollResponse;
import com.common.util.GsonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class EsClient {

    /**
     * Elasticsearch允许等待的时间 (分钟)
     */
    private static final String  ES_TIME_OUT_MINUTES = "5";

    /**
     * ES 数据源
     */
    private static ElasticsearchClient elasticsearchClient;

    public static void initEsConfig(ElasticsearchClient client){
        elasticsearchClient=client;
    }

    private static ElasticsearchClient getClient(){
        if (ObjectUtils.isEmpty(elasticsearchClient)){

        }
        return elasticsearchClient;
    }


    public static <T extends EsBaseEntity> T save(T t) {
        Document document = t.getClass().getAnnotation(Document.class);
        if (document == null) {
            throw new RuntimeException("这个对象没有 Document 注解");
        }
        String indexName = document.indexName();
        if (StringUtils.isEmpty(indexName)) {
            throw new RuntimeException("没有声明 indexName 索引名");
        }
        if (StringUtils.isEmpty(t.unique())) {
            throw new RuntimeException("唯一主键不能为空");
        }
        try {
            ExistsRequest existsRequest= ExistsRequest.of(b-> b.index(document.indexName()));
            BooleanResponse exists = getClient().indices().exists(existsRequest);
            if (!exists.value()){
                CreateIndexRequest createIndexRequest=CreateIndexRequest.of(b->b.index(indexName)
                        .settings(IndexSettings.of(a-> new IndexSettings.Builder().numberOfReplicas(document.replicas()).numberOfShards(document.shards()))
                ));
                getClient().indices().create(createIndexRequest);
            }
            getClient().index(IndexRequest.of(b->b.index(indexName).id(t.unique()).document(t)));
            return t;
        } catch (IOException e) {
            throw new RuntimeException("es save error",e);
        }
    }

    public static  <T extends EsBaseEntity> void delete(Class<T> clazz, String id) {
        Document document = clazz.getAnnotation(Document.class);
        if (document == null) {
            throw new RuntimeException("这个对象没有 Document 注解");
        }
        String indexName = document.indexName();
        if (indexName == null || indexName.equals("")) {
            throw new RuntimeException("没有声明 indexName 索引名");
        }
        String typeName = document.type();
        if (typeName == null || typeName.equals("")) {
            throw new RuntimeException("没有声明 typeName 索引名");
        }
        if (id == null) {
            throw new RuntimeException("唯一主键不能为空");
        }
        try {
            DeleteRequest deleteRequest = DeleteRequest.of(b -> b.index(indexName).id(id));
            DeleteResponse delete = getClient().delete(deleteRequest);
        }catch (Exception e){
            throw new RuntimeException("delete error ",e);
        }
    }

    public static  <T extends EsBaseEntity, ID extends Serializable> T findById(Class<T> clazz, ID id) {
        Document document = clazz.getAnnotation(Document.class);
        if (document == null) {
            throw new RuntimeException("这个对象没有 Document 注解");
        }
        String indexName = document.indexName();
        if (indexName == null || indexName.equals("")) {
            throw new RuntimeException("没有声明 indexName 索引名");
        }
        if (id == null) {
            throw new RuntimeException("id 不可以为空");
        }

        try {
            GetRequest getRequest = GetRequest.of(b -> b.id(document.indexName()).id(String.valueOf(id)));
            GetResponse<T> getResponse = getClient().get(getRequest, clazz);
            return getResponse.source();
        } catch (IOException e) {
            throw new RuntimeException("findById error ",e);
        }
    }

    public static <T extends EsBaseEntity> List<T> findByQuery(Class<T> clazz, Query query, List<SortOptions> sortList) {
        Document document = clazz.getAnnotation(Document.class);
        if (document == null) {
            throw new RuntimeException("这个对象没有 Document 注解");
        }
        String indexName = document.indexName();
        if (indexName == null || indexName.equals("")) {
            throw new RuntimeException("没有声明 indexName 索引名");
        }
        if (query == null) {
            throw new RuntimeException("查询条件不可以为空");
        }
        try {
            SearchRequest searchRequest = SearchRequest.of(b -> b.index(document.indexName()).query(query).sort(sortList));
            SearchResponse search = getClient().search(searchRequest,clazz);
            List<T> result = new ArrayList();
            List hits = search.hits().hits();
            for (Object hit:hits){
                result.add(GsonUtil.gson.fromJson(GsonUtil.toJSON(hit),clazz));
            }
            return result;
        }catch (Exception e){
            log.error("es query error",e);
        }
        return new ArrayList<>();
    }

    public static <T extends EsBaseEntity> EsPageResponse<T> findPageByQuery(Class<T> clazz, Query query, List<SortOptions> sortOptionsList, PageRequest pageRequest){
        Document document = clazz.getAnnotation(Document.class);
        if (document == null) {
            throw new RuntimeException("这个对象没有 Document 注解");
        }
        String indexName = document.indexName();
        if (indexName == null || indexName.equals("")) {
            throw new RuntimeException("没有声明 indexName 索引名");
        }
        if (query == null) {
            throw new RuntimeException("查询条件不可以为空");
        }
        if (pageRequest == null) {
            throw new RuntimeException("分页对象不可以为空");
        }
        try {
            SearchRequest searchRequest = SearchRequest.of(b -> b.index(document.indexName()).query(query)
                    .sort(sortOptionsList).from(pageRequest.getOffset()).size(pageRequest.getPage_size()));
            SearchResponse searchResponse = getClient().search(searchRequest,clazz);
            List<T> result = new ArrayList();
            List hits = searchResponse.hits().hits();
            for (Object hit:hits){
                result.add(GsonUtil.gson.fromJson(GsonUtil.toJSON(hit),clazz));
            }
            EsPageResponse pageData = new EsPageResponse();
            pageData.setPageSize(pageRequest.getPage_size());
            pageData.setPageNum(pageRequest.getPage_num());
            pageData.setDatas(result);
            pageData.setTotalCount(searchResponse.hits().total().value());
            return pageData;
        }catch (Exception e){
            log.error("es search error",e);
        }
        return null;
    }

    public static <T extends EsBaseEntity> long countByQuery(Class<T> clazz, Query query){
        Document document = clazz.getAnnotation(Document.class);
        if (document == null) {
            throw new RuntimeException("这个对象没有 Document 注解");
        }
        String indexName = document.indexName();
        if (indexName == null || indexName.equals("")) {
            throw new RuntimeException("没有声明 indexName 索引名");
        }
        if (query == null) {
            throw new RuntimeException("查询条件不可以为空");
        }
        try {
            SearchRequest searchRequest = SearchRequest.of(b -> b.index(document.indexName()).query(query));
            SearchResponse searchResponse = getClient().search(searchRequest,clazz);
            return searchResponse.hits().total().value();
        }catch (Exception e){
            log.error("es countByQuery error",e);
        }
        return 0;
    }

    public static <T extends EsBaseEntity> EsScrollResponse<T> listByQueryScroll(Class<T> clazz, Query query, String scrollId, int size){
        // 参数校验
        if (size < 1 || size > 200) {
            throw new RuntimeException("ES 查询一次请求的数了超出范围，请在 1-200 之间");
        }
        // 返回结果初始化
        List<T> result = new ArrayList<>();
        // 获取文档信息
        Document document = clazz.getAnnotation(Document.class);
        // 第一次查询没有游标，获取数据并记录游标
        try {
            EsScrollResponse response=new EsScrollResponse();
            SearchResponse searchResponse=null;
            if (StringUtils.isEmpty(scrollId)){
                SearchRequest searchRequest = SearchRequest.of(b -> b.index(document.indexName()).query(query).size(size)
                        .scroll(Time.of(a -> a.time(ES_TIME_OUT_MINUTES))));
                searchResponse = getClient().search(searchRequest,clazz);

            }else {
                ScrollRequest of = ScrollRequest.of(b -> b.scrollId(scrollId));
                searchResponse = getClient().scroll(of,clazz);
            }
            for (Object hit:searchResponse.hits().hits()){
                result.add(GsonUtil.gson.fromJson(GsonUtil.toJSON(hit),clazz));
            }
            response.setData(result);
            response.setScrollId(scrollId);
            return response;
        }catch (Exception e){
            log.error("es scroll error",e);
        }
        return null;
    }

}
