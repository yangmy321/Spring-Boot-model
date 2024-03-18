package top.test.boot.database.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import top.test.boot.database.entity.Article;

@SpringBootTest
class ArticleJpaServiceImplTest {
    @Resource
    private ArticleJpaServiceImpl articleJpaService;
    @Test
    void saveArticle() {
        Article article = Article.builder()
                .id(1)
                .author("YMY")
                .content("Spring Boot")
                .title("Spring Boot")
                .build();
        articleJpaService.saveArticle(article);
    }

    @Test
    void updateArticle() {
        Article article = Article.builder()
                .author("YMY1")
                .content("Spring Boot")
                .title("Spring BOOt")
                .id(3)
                .build();
        articleJpaService.updateArticle(article);
    }

    @Test
    void getArticleById() {
        articleJpaService.getArticleById(2);
    }

    @Test
    void findAll() {
        articleJpaService.findAll();
    }

    @Test
    void deleteArticle() {
        articleJpaService.deleteArticle(2);
    }
}