package opsy.data;

import opsy.entities.Articles;
import opsy.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticlesRepository extends CrudRepository<Articles, Integer> {
    @Override
    List<Articles> findAll();

    Articles findByArticleId(long id);

    List<Articles> findAllByAuthor(Users users);

    List<Articles> findAllByOrderByPublishdateAsc();

    void deleteByArticleId(long id);

    void deleteAllByAuthor(Users users);

}
