package com.urlShortner.project.Repository;

import com.urlShortner.project.entity.UrlDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElasticSearchUrlRepository extends ElasticsearchRepository<UrlDocument,Long> {

    List<UrlDocument> findByOriginalUrlContaining(String keyword);



}
