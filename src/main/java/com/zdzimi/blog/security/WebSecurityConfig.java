package com.zdzimi.blog.security;

import com.zdzimi.blog.dao.UserRepository;
import com.zdzimi.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;

@Controller
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserRepository userRepository, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/add-comment").hasRole("USER")
                .antMatchers("/admin",
                        "/articles","/articles-by-chapter","/delete-article","/save-article",
                        "/chapters","/delete-chapter","/save-chapter",
                        "/comments","/comments-by-article","/delete-comment","/answer",
                        "/paragraphs","/paragraphs-by-article","/delete-paragraph","/save-paragraph",
                        "/photos","/delete-photo","/add-photo",
                        "/topbar","/delete-topbar","/save-top").hasRole("ADMIN")
                .and()
                .formLogin().permitAll();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    protected void start(){
        User user = new User("user", passwordEncoder().encode("user"), "ROLE_USER");
        User admin = new User("admin", passwordEncoder().encode("admin"), "ROLE_ADMIN");
        userRepository.save(user);
        userRepository.save(admin);
    }
}
