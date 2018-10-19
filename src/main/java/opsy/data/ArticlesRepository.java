package opsy.data;

import opsy.entities.Articles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticlesRepository extends CrudRepository<Articles, Integer> {
    @Override
    List<Articles> findAll();

    Articles findByArticleId(long id);

//    @Query("select t.title, t.publishdate, t.author from Articles t")
//    List<Articles> findArticlesRepresentation();
}
