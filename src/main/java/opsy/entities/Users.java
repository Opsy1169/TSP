package opsy.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "workingschema")
public class Users {
    private long userId;
    private String login;
    private String password;
    private Boolean isadmin;
    private Boolean ismoder;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "isadmin")
    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    @Basic
    @Column(name = "ismoder")
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
