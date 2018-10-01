package com.tecstack.springdataelasticsearch.service;

import com.tecstack.springdataelasticsearch.domain.Article;
import java.util.List;
import java.util.Optional;

public interface ISearchService {

    Optional<Article> byTitle(String title);

    List<Article> byFuzzyTitle(String title);

}
