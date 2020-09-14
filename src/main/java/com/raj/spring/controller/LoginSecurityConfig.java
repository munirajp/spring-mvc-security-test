package com.raj.spring.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {


	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		/*auth.jdbcAuthentication().dataSource(dataSource())
        .usersByUsernameQuery("select username, password,enabled from users where username=?")
        .authoritiesByUsernameQuery("select u.username, ur.user_role from USERS u, USERS_ROLES ur where u.user_id = ur.userid and u.username = ?"); 
		*/
		auth.inMemoryAuthentication().withUser("raja").password("raja").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");

	}
	
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/Success").access("hasRole('ROLE_USER')")
			.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
		.and()
			.formLogin().loginPage("/loginPage")
			.successHandler(new CustomHandler())
			.failureUrl("/loginPage?error")
			
		.and()
			.logout().logoutSuccessUrl("/loginPage?logout"); 

	}
	
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/mysql");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root123");
        return driverManagerDataSource;
    }
	
	
}
