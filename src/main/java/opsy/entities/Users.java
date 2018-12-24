package opsy.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", catalog = "blog")
public class Users {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "isadmin")
    private Boolean isadmin;
    
    @Basic
    @Column(name = "ismoder")
    private Boolean ismoder;



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }


    public Boolean getIsmoder() {
        return ismoder;
    }

    public void setIsmoder(Boolean ismoder) {
        this.ismoder = ismoder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId == users.userId &&
                Objects.equals(login, users.login) &&
                Objects.equals(password, users.password) &&
                Objects.equals(isadmin, users.isadmin) &&
                Objects.equals(ismoder, users.ismoder);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, login, password, isadmin, ismoder);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isadmin=" + isadmin +
                ", ismoder=" + ismoder +
                '}';
    }
}
