package top.test.boot.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.test.boot.database.entity.Article;

/**
 * @author 34746
 */
@Mapper
public interface ArticleMapper {
    void insertArticle(Article article);
}