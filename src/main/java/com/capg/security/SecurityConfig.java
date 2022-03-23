/*
package com.capg.security;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected  void configure(AuthenticationManagerBuilder auth)throws Exception{
        super.configure(auth);
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        super.configure(http);
        http.csrf().disable();
    }

}*/
package com.capg.security;

import com.capg.entity.User;
import com.capg.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    CustomerService customerService;

    @Override
    protected  void configure(AuthenticationManagerBuilder auth)throws Exception{
        List<User> usersList = customerService.readUsers();
        for(User user:usersList) {
            auth
                    .inMemoryAuthentication()
                    .withUser(user.getUsername())
                    .password(passwordEncoder().encode(user.getPassword()))
                    .roles(user.getRole());
        }
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .antMatchers("/customer/create","/customer/deleteCustomer","/customer/deleteProduct")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}