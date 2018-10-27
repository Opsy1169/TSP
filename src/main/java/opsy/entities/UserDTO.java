package opsy.entities;


import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

public class UserDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
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
