package com.tecstack.springdataelasticsearch.service;


import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;

import com.tecstack.springdataelasticsearch.domain.Article;
import com.tecstack.springdataelasticsearch.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements ISearchService {

    private final ArticleRepository articleRepository;
    private ElasticsearchTemplate template;

    public SearchService(ArticleRepository articleRepository,
        ElasticsearchTemplate elasticsearchTemplate) {
        this.articleRepository = articleRepository;
        this.template = elasticsearchTemplate;
    }

    @Override
    public Optional<Article> byTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    @Override
    public List<Article> byFuzzyTitle(String title) {
        SearchQuery query = new NativeSearchQueryBuilder()
            .withQuery(fuzzyQuery("title", title)).build();
        return template.queryForList(query, Article.class);
    }
}
