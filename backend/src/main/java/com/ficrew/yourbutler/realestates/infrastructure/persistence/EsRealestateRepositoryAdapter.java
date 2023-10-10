package com.ficrew.yourbutler.realestates.infrastructure.persistence;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import com.ficrew.yourbutler.realestates.domain.repository.SearchRepository;
import com.ficrew.yourbutler.realestates.infrastructure.persistence.es.ElasticsearchRealestateRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EsRealestateRepositoryAdapter implements SearchRepository {

    private final ElasticsearchRealestateRepository elasticsearchRepository;

    @Override
    public RealestateDocument findByRealestateId(Long articleNo) {
        return elasticsearchRepository.findByArticleNo(articleNo);
    }

    @Override
    public List<RealestateDocument> searchRecentDeal2() {
        return elasticsearchRepository.findTop2ByRealEstateTypeNameOrderByArticleConfirmYmdDesc("매매");
    }

    @Override
    public List<RealestateDocument> searchRecentRent2() {
        return elasticsearchRepository.findTop2ByRealEstateTypeNameOrderByArticleConfirmYmdDesc("월세");
    }

    @Override
    public List<RealestateDocument> searchRecentLease1() {
        return elasticsearchRepository.findTop1ByRealEstateTypeNameOrderByArticleConfirmYmdDesc("전세");
    }



}
