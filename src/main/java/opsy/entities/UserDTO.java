package opsy.entities;



import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * Объект, который, вроде, будет лежать в аутентификации
 * А мб он просто используется, чтобы прочекать логин-пароль в БД
 * Просто вспомогательный объект, чтобы не ебаться лишний раз
 */
public class UserDTO {

    private String username;

    private String password;

    private String confirmPass;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {

        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
