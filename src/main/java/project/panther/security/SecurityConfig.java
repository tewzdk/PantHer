/* Written by: Max Møller Hoffmeyer & Sidney Schultz */

package project.panther.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    Databasen er autowired hvilke gør at spring sørger for at
    finde ud af hvad vi bruger som database.
    Det er vist i application properties
     */
    @Autowired
    DataSource dataSource;

    /*
    Bruger SQL query til at snakke med databasen.
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoderpanther())
                .usersByUsernameQuery("select mail as username, kodeord as password, enabled from brugere where mail=?")
                .authoritiesByUsernameQuery("select mail as username, role from brugere where mail=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/pictures/**", "/scripts/**", "/stylesheets/**" , "/lidt-om-os", "/elepantprojektet", "/samarbejdspartnere", "/opret-bruger", "/create-marker")
                .permitAll()
                .antMatchers("/mainpage", "/bruger")
                .authenticated()
                .and()
                .formLogin().loginPage("/").defaultSuccessUrl("/mainpage")
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout()
                .permitAll();
        http.exceptionHandling().accessDeniedPage("/");
    }

    @Bean
    public PasswordEncoder passwordEncoderpanther() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}