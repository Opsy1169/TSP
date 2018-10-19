package opsy.data;

import opsy.entities.Comments;
import opsy.entities.Users;
import opsy.entities.aComments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<aComments, Integer> {

    List<aComments> findAll();
    List<aComments> findAllByAuthorId(Users users);
    List<aComments> findAllByArticle(int articleId);
}
