package com.common.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.common.es.client.EsClient;
import com.common.util.EnvUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EsConfig {

    public void init() throws UnknownHostException {
        Environment environment = EnvUtil.getEnvironment();
        String clusterNodes = environment.getProperty("es.config.clusterNodes");
        if (ObjectUtils.isEmpty(clusterNodes)){
            throw new RuntimeException("es.config.clusterNodes is empty");
        }
        String[] ipPortArr = clusterNodes.split(",");//逗号分隔
        HttpHost[] httpHosts = new HttpHost[ipPortArr.length];
        for (int i = 0; i < ipPortArr.length; i++) {
            String[] ip_port = ipPortArr[i].split(":");//冒号分隔
            httpHosts[i] = new HttpHost(InetAddress.getByName(ip_port[0]), Integer.parseInt(ip_port[1]), "http");
        }
        RestClient restClient = RestClient.builder(httpHosts).setHttpClientConfigCallback(
                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(initCredentialsProvider())).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(transport);
        EsClient.initEsConfig(elasticsearchClient);
    }

    private CredentialsProvider initCredentialsProvider() {
        Environment environment = EnvUtil.getEnvironment();
        String userName = environment.getProperty("es.config.userName");
        String password = environment.getProperty("es.config.password");
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName, password));
        return credentialsProvider;
    }


}
