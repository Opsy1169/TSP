package opsy.config;


import opsy.data.UsersRepository;
import opsy.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder authenticationManager) throws Exception{
        authenticationManager.userDetailsService(getUserDetailsService())
        .passwordEncoder(encoder());
    }


    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable(); //.authorizeRequests().anyRequest().permitAll().and();

        http.authorizeRequests().antMatchers("/user/registration").permitAll().and();

        http.authorizeRequests().antMatchers("/admin**")
                .access("hasAuthority('ROLE_ADMIN')")
                .and();

        http.authorizeRequests().antMatchers("/**")
                .access("hasAuthority('ROLE_USER')").and();





        http.formLogin().loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/router", true)
                .failureUrl("/forbidden")
                .permitAll().and();

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true).and();

        http.exceptionHandling().accessDeniedPage("/forbidden");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

        return bCryptPasswordEncoder;
    }

    @Bean(name = "service")
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUserDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

}
