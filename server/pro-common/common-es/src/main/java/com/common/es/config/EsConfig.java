package com.common.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.common.es.client.EsClient;
import com.common.util.LogUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EsConfig {

    EsProperties esProperties;

    public EsConfig(EsProperties esProperties) {
        this.esProperties=esProperties;
    }

    public void init() throws UnknownHostException {
        String clusterNodes = esProperties.getClusterNodes();
        if (ObjectUtils.isEmpty(clusterNodes)){
            throw new RuntimeException("es.config.clusterNodes is empty");
        }
        String[] ipPortArr = clusterNodes.split(",");
        HttpHost[] httpHosts = new HttpHost[ipPortArr.length];
        for (int i = 0; i < ipPortArr.length; i++) {
            String[] ipPort = ipPortArr[i].split(":");
            httpHosts[i] = new HttpHost(InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1]), "http");
        }
        RestClient restClient = RestClient.builder(httpHosts).setHttpClientConfigCallback(
                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(initCredentialsProvider())).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(transport);
        EsClient.initEsConfig(elasticsearchClient);
        LogUtil.info("elasticsearch init success");
    }

    private CredentialsProvider initCredentialsProvider() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(esProperties.getUserName(), esProperties.getPassword()));
        return credentialsProvider;
    }

}
