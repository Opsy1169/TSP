package opsy.data;

import opsy.entities.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface UsersRepository extends CrudRepository<Users, Integer> {
    @Override
    Users save(Users s);

    Users findByLogin(String login);

    List<Users> findAll();

    Users findByUserId(long id);


}
