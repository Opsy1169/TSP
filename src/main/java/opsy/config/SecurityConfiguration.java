//package opsy.config;
//
//
//import opsy.data.UsersRepository;
//import opsy.security.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//    /**
//     * Какая-то внутренняя беда спринга для проверки паролей и запихивания Юзер-объекта
//     * в объект Аутентификации
//     */
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder authenticationManager) throws Exception{
//        authenticationManager.userDetailsService(getUserDetailsService())
//        .passwordEncoder(encoder());
//    }
//
//
//    /**
//     * Конфигурационный метод, здесь указывается распределения доступа к ресурсам по ролям,
//     * адреса обработки логиа и логаута, перевод пользователя на страницы при удачном и неудачном логине
//     * Так же выставлен параметр, чтобы пользователя всегда перенаправляло на домашнюю страницу, а не на тот юрл, который
//     * он вбивал до авторизации
//     * Метод можно разбить на несколько частей(здесь уже разбито, но можно и дальше разделять его логически)
//     */
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable(); //.authorizeRequests().anyRequest().permitAll().and();
//
//        http.authorizeRequests().antMatchers("/user/registration").permitAll().and();
//
//        http.authorizeRequests().antMatchers("/admin**")
//                .access("hasAuthority('ROLE_ADMIN')")
//                .and();
//
//        http.authorizeRequests().antMatchers("/**")
//                .access("hasAuthority('ROLE_USER')").and();
//
//
//
//
//
//        http.formLogin().loginPage("/")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/router", true)
//                .failureUrl("/forbidden")
//                .permitAll().and();
//
//        http.logout()
//                .permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true).and();
//
//        http.exceptionHandling().accessDeniedPage("/forbidden");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider());
//    }
//
//
//    /**
//     * Какие-то проблемы возникли с внедрением бинов, поэтому эти мне пришлось убрать из основного конфиг файла в
//     * этот, благо, логика допускает
//     */
//    @Bean
//    public BCryptPasswordEncoder encoder(){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
//
//        return bCryptPasswordEncoder;
//    }
//
//    @Bean(name = "service")
//    public UserDetailsService getUserDetailsService(){
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(getUserDetailsService());
//        authProvider.setPasswordEncoder(encoder());
//        return authProvider;
//    }
//
//}
