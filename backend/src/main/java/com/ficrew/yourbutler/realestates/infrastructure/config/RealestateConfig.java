package com.ficrew.yourbutler.realestates.infrastructure.config;

import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import com.ficrew.yourbutler.realestates.application.CalculateColorProcessor;
import com.ficrew.yourbutler.realestates.application.CheckBookmarkStatusListProcessor;
import com.ficrew.yourbutler.realestates.application.CheckBookmarkStatusProcessor;
import com.ficrew.yourbutler.realestates.application.CreateBookmarkProcessor;
import com.ficrew.yourbutler.realestates.application.DeleteBookmarkProcessor;
import com.ficrew.yourbutler.realestates.application.GetRecentListProcessor;
import com.ficrew.yourbutler.realestates.application.SearchAllRealestatesProcessor;
import com.ficrew.yourbutler.realestates.application.SearchBookmarkListProcessor;
import com.ficrew.yourbutler.realestates.application.SearchRealestateDetailsProcessor;
import com.ficrew.yourbutler.realestates.domain.service.CalculateService;
import com.ficrew.yourbutler.realestates.domain.repository.BookmarkRepository;
import com.ficrew.yourbutler.realestates.domain.repository.SearchRepository;
import com.ficrew.yourbutler.realestates.infrastructure.persistence.es.ElasticsearchRealestateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class RealestateConfig {

    @Bean
    public SearchRealestateDetailsProcessor searchRealestateDetailsProcessor(
        SearchRepository searchRepository
    ) {
        return new SearchRealestateDetailsProcessor(
            searchRepository
        );
    }

    @Bean
    public SearchAllRealestatesProcessor searchAllRealestatesProcessor(
        ElasticsearchRestTemplate elasticsearchRestTemplate
    ) {
        return new SearchAllRealestatesProcessor(
            elasticsearchRestTemplate
        );
    }

    @Bean
    public CheckBookmarkStatusProcessor bookmarkCheckProcessor(
        BookmarkRepository bookmarkRepository
    ) {
        return new CheckBookmarkStatusProcessor(
            bookmarkRepository
        );
    }

    @Bean
    public CreateBookmarkProcessor createBookmarkProcessor(
        BookmarkRepository bookmarkRepository,
        MemberRepository memberRepository
    ) {
        return new CreateBookmarkProcessor(
            bookmarkRepository,
            memberRepository
        );
    }

    @Bean
    public DeleteBookmarkProcessor deleteBookmarkProcessor(
        BookmarkRepository bookmarkRepository
    ) {
        return new DeleteBookmarkProcessor(
            bookmarkRepository
        );
    }

    @Bean
    public SearchBookmarkListProcessor searchBookmarkListProcessor(
        ElasticsearchRealestateRepository elasticsearchRealestateRepository,
        BookmarkRepository bookmarkRepository
    ) {
        return new SearchBookmarkListProcessor(
            elasticsearchRealestateRepository,
            bookmarkRepository
        );
    }

    @Bean
    public CheckBookmarkStatusListProcessor checkBookmarkStatusListProcessor(
        BookmarkRepository bookmarkRepository
    ) {
        return new CheckBookmarkStatusListProcessor(bookmarkRepository);
    }

    @Bean
    public CalculateColorProcessor calculateColorProcessor(
        CalculateService calculateService
    ) {
        return new CalculateColorProcessor(
            calculateService
        );
    }

    @Bean
    GetRecentListProcessor getRecentListProcessor(
        SearchRepository searchRepository
    ) {
        return new GetRecentListProcessor(searchRepository);
    }
}
