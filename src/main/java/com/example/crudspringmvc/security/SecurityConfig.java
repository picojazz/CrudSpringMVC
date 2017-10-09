package com.example.crudspringmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* auth.inMemoryAuthentication()
                .withUser("user").password("azerty").roles("user");
        auth.inMemoryAuthentication()
                .withUser("admin").password("azerty").roles("admin");*/

       auth.jdbcAuthentication().dataSource(dataSource)
               .usersByUsernameQuery("select login as principal,password as credentials ,active from users where login =?")

               .passwordEncoder(new Md5PasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/all").permitAll();
        http.authorizeRequests().antMatchers("/new","/delete","/edit").hasRole("admin");
    }
}
