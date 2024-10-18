package com.czc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Autowired
    protected DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/MedicineData").permitAll()
                .antMatchers("/userLogin").permitAll()
                .antMatchers("/page/**").permitAll()
                .antMatchers("/client/**").permitAll()
                .antMatchers("/back/page/backuser/**").hasRole("manager")
                .antMatchers("/back/page/admin/*").hasRole("manager")
                .antMatchers("/back/page/common/*").hasRole("common")
                .anyRequest().authenticated();
        /**
         * 处理提交登录的请求
         */
        http.formLogin()
                .loginPage("/userLogin")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/admin");
        http.logout()
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/");
        http.rememberMe()
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(200)
                .tokenRepository(tokenRepository());

    }
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jr=new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }

    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        web.ignoring().antMatchers("/**/*.js", "/lang/*.json", "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.html",
                "/**/*.png","/**/*.gif","/**/*.jpg","/**/*.ico","/**/*.otf","/**/*.eot","/**/*.svg","/**/*.ttf","/**/*.woff","/**/*.woff2",
                "/**/*.less","/**/*.scss");
    }

}
