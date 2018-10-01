package com.tecstack.springdataelasticsearch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.tecstack.springdataelasticsearch.domain.Article;
import com.tecstack.springdataelasticsearch.repository.ArticleRepository;
import com.tecstack.springdataelasticsearch.service.ISearchService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {

    private static final String TITILE = "Spring";

    @Autowired
    private ISearchService search;

    @Autowired
    private ArticleRepository articleRepository;

    @Before
    public void before() {
        articleRepository.deleteAll();

        Article article = new Article(UUID.randomUUID().toString(), TITILE,"text", new Date());
        articleRepository.save(article);
    }

    @Test
    public void searchByTitle() {

        Optional<Article> articleByTitle = search.byTitle(TITILE);

        assertThat(articleByTitle.isPresent(), CoreMatchers.is(Boolean.TRUE));
        assertThat(articleByTitle.get().getTitle(), CoreMatchers.is(TITILE));
    }

    @Test
    public void searchByFuzzyTitle() {
        List<Article> articles = search.byFuzzyTitle("Tpring");

        assertThat(articles, hasSize(1));
        assertThat(articles.get(0).getTitle(), CoreMatchers.is(TITILE));
    }
}
