package com.tecstack.springdataelasticsearch.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "article", type = "article")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    private String id;

    private String title;

    private String text;

    @Field(type = FieldType.Date)
    private Date createdDate = new Date();
}
