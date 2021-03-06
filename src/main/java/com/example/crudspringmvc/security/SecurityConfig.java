package com.example.crudspringmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
               .usersByUsernameQuery("select username as principal,password as credentials ,active from user where username =?")
               .authoritiesByUsernameQuery("select u.username as principal,r.role as role from user u ,role r, users_roles ur where u.id=ur.user_id and r.id=ur.role_id and u.username=?")
               .rolePrefix("ROLE_")
               .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/me").authenticated();
        //http.authorizeRequests().antMatchers("/all").permitAll();
        http.authorizeRequests().antMatchers("/admin/*").hasRole("admin");
        http.exceptionHandling().accessDeniedPage("/403");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
       // web.ignoring().antMatchers("/css/*");
    }
}
