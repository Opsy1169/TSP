package opsy.config;

import opsy.entities.DAO;
import opsy.security.UserDetailsServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages="opsy")
@EnableJpaRepositories(basePackages = {"opsy"})
@EnableTransactionManagement
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	UserDetailsService service;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/usershome").setViewName("usershome");
		registry.addViewController("/adminhome").setViewName("adminhome");
		registry.addViewController("/restricted").setViewName("restricted");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean(name = "dataSource")
	public DataSource dataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/blog");
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setPassword("");
		ds.setUsername("postgres");
		return ds;
	}

//	@Bean
//    public PlatformTransactionManager hibernateTransactionManager(){
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
////        transactionManager.setSessionFactory(entityManagerFactory().unwrap(SessionFactory.class));
//        return transactionManager;
//    }

	@Bean(name = "dao")
	public DAO gatDao(){
		return new DAO();
	}

//	@Bean(name = "sessionFactory")
//    public LocalSessionFactoryBean sessionFactory(){
//	    LocalSessionFactoryBean session = new LocalSessionFactoryBean();
//	    session.setDataSource(dataSource());
//	    session.setPackagesToScan(new String[] {"opsy.entities"});
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
//        session.setHibernateProperties(properties);
//	    return session;
//    }

    @Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager manager =  new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactory());
		return manager;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setPackagesToScan(new String[] {"opsy"});
		factory.afterPropertiesSet();


		return factory.getObject();
	}









}


