package com.ficrew.yourbutler.realestates.infrastructure.config;

import com.ficrew.yourbutler.realestates.domain.repository.BookmarkRepository;
import com.ficrew.yourbutler.realestates.domain.repository.SearchRepository;
import com.ficrew.yourbutler.realestates.infrastructure.persistence.EsRealestateRepositoryAdapter;
import com.ficrew.yourbutler.realestates.infrastructure.persistence.RealestateRepositoryAdapter;
import com.ficrew.yourbutler.realestates.infrastructure.persistence.es.ElasticsearchRealestateRepository;
import com.ficrew.yourbutler.realestates.infrastructure.persistence.jpa.JpaBookmarkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RealestateRepositoryConfig {

    @Bean
    public SearchRepository searchRepository(
        ElasticsearchRealestateRepository elasticsearchRealestateRepository
    ) {
        return new EsRealestateRepositoryAdapter(
            elasticsearchRealestateRepository
        );
    }

    @Bean
    public BookmarkRepository bookmarkRepository(
        JpaBookmarkRepository jpaBookmarkRepository
    ) {
        return new RealestateRepositoryAdapter(
            jpaBookmarkRepository
        );
    }


}
