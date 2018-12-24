package opsy.data;

import opsy.entities.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface UsersRepository extends CrudRepository<Users, Integer> {

    Users findByLogin(String login);

    List<Users> findAll();

    Users findByUserId(long id);


    /**
     * Пришлось переписать метод, так как обычный Save, почему-то всегда использовал нулевой айдишник
     * и не мог сохранить сущность
     * Я подозреваю, что это могут быть какие-то мои проблемы(на моей машине), и не обязательно всем пользоваться этим методом,
     * хотя я затрудняюсь сказать, чем он хуже встроенного
     *Для апдейта сущностей можно по-прежнему использовать встроенный Save
     * @param login
     * @param password
     * @param isModer
     * @param isAdmin
     */
    @Modifying
    @Query(value = "insert into users (login, password, isadmin, ismoder) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void rawSave(String login, String password, boolean isModer, boolean isAdmin);


}
