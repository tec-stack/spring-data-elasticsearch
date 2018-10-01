package com.tecstack.springdataelasticsearch.repository;


import com.tecstack.springdataelasticsearch.domain.Article;
import java.util.Optional;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Optional<Article> findByTitle(String title);

}
