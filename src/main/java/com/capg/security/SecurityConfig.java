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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected  void configure(AuthenticationManagerBuilder auth)throws Exception{

            auth
                    .inMemoryAuthentication()
                    .withUser("sanjay")
                    .password(passwordEncoder().encode("1234"))
                    .roles("CUSTOMER")
                    .and()
                    .withUser("admin")
                    .password(passwordEncoder().encode("admin123"))
                    .roles("ADMIN");


    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .antMatchers("/customer/create","/customer/delete")
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