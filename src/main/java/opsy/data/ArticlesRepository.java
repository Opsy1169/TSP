package opsy.data;

import opsy.entities.Articles;
import opsy.entities.Categories;
import opsy.entities.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticlesRepository extends CrudRepository<Articles, Integer> {
    @Override
    List<Articles> findAll();

    Articles findByArticleId(long id);

    List<Articles> findAllByAuthor(Users users);

    List<Articles> findAllByOrderByPublishdateAsc();

    void deleteByArticleId(long id);

    void deleteAllByAuthor(Users users);

    @Modifying
    @Query(value = "update articles set article_body = ?0, title = ?1 where article_id = ?2", nativeQuery = true)
    void updateArticle( String body,  String title,  long id);

    List<Articles> findAllByCategories(Categories categories);

}
