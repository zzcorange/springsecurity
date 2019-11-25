package com.zzc.security.config;

import com.zzc.security.filter.VerifiCationCodeFilter;
import com.zzc.security.handler.LoginSuccessHandler;
import com.zzc.security.handler.MyAuthenticationFailureHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author chengzi
 * @Date 2019/11/1320:14
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/api/*").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
                .antMatchers("/app/api/**","/captcha.jpg").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/myLogin.html")
                .loginProcessingUrl("/login").successHandler(new LoginSuccessHandler()).failureHandler(new MyAuthenticationFailureHandler())
                .permitAll()
                .and().csrf().disable();
        http.addFilterBefore(new VerifiCationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
