package opsy.security;

import opsy.entities.Users;
import org.springframework.security.core.userdetails.User;

public interface UserService {
    Users getUser(String login);
}
