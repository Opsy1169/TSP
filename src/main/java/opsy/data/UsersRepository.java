package opsy.data;

import opsy.entities.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Users getByLogin(String login);
    List<Users> findAll();
}
