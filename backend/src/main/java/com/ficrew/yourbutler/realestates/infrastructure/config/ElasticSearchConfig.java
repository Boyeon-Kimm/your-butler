package com.ficrew.yourbutler.realestates.infrastructure.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
    @Override
    public RestHighLevelClient elasticsearchClient() {
        // http port 와 통신할 주소, 인증 정보 추가
        ClientConfiguration configuration = ClientConfiguration.builder()
            .connectedTo("j9a405.p.ssafy.io:3200")
            .withBasicAuth("elastic", "changeme")  // 인증 정보 추가
            .build();
        return RestClients.create(configuration).rest();
    }
}