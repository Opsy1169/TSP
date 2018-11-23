package opsy.security;


import opsy.data.UsersRepository;
import opsy.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    /**
     *Эту логику использует спринг внутри, чтобы проверить, есть ли такой пользователь в бд, чтобы присвоить нужные
     * роли и т.д.
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = usersRepository.findByLogin(s);
        Set<GrantedAuthority> roles = new HashSet<>();
        if(user.getIsadmin()) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(user.getLogin(), user.getPassword(), roles);
    }
}
