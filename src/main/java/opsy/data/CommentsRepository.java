package opsy.data;

import opsy.entities.Users;
import opsy.entities.Comments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comments, Integer> {

    List<Comments> findAll();
    List<Comments> findAllByAuthorId(Users users);
    List<Comments> findAllByArticle(int articleId);
    void deleteAllByArticle(int article);
    void deleteAllByAuthorId(Users users);
}
