package com.finalproject.demo.config;

import com.finalproject.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final ApplicationProperties applicationProperties;


    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, ApplicationProperties applicationProperties) {
        this.userDetailsService = userDetailsService;
        this.applicationProperties = applicationProperties;
    }



    /**test*/
    @Autowired
    DataSource dataSource;

    /**test*/
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/registration",
                        "/forgottenPassword",
                        "/demoPage",
                        "/confirmRegistration",
                        "/success"
                ).not().fullyAuthenticated()
                .antMatchers("/home/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/adminDB/**").hasRole("ADMIN_DB")
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/default")
                .permitAll()

                /*.and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/

                /**test*/
                .and()
                .rememberMe()
                .rememberMeParameter("ZNAIDOO-remember-me")
                .tokenRepository(persistentTokenRepository())
                .key("secretkey")
                .tokenValiditySeconds(applicationProperties.getTokenValiditySeconds())

                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
        ;
    }
}
