package com.ficrew.yourbutler.realestates.infrastructure.persistence.es;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchRealestateRepository extends ElasticsearchRepository<RealestateDocument, String> {
    RealestateDocument findByArticleNo(Long articleNo);

    List<RealestateDocument> findTop2ByRealEstateTypeNameOrderByArticleConfirmYmdDesc(String realEstateTypeName);

    List<RealestateDocument> findTop1ByRealEstateTypeNameOrderByArticleConfirmYmdDesc(String realEstateTypeName);

}
