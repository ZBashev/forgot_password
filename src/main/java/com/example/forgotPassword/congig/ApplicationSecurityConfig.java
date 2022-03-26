package com.example.forgotPassword.congig;


import com.example.forgotPassword.security.BoilSecurityDetailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final BoilSecurityDetailService boilSecurityDetailService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(BoilSecurityDetailService boilSecurityDetailService,
                                     PasswordEncoder passwordEncoder) {
        this.boilSecurityDetailService = boilSecurityDetailService;


        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer = http.
                authorizeRequests().
                antMatchers("/css/**", "/images/**", "/js/**").permitAll().
                antMatchers(  "/users/login",
                        "/users/register", "/",
                        "/users/reset-password", "/reset").permitAll().
                antMatchers("/**").authenticated().
                and().
                formLogin().
                loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/home").
                failureForwardUrl("/users/login-error").
                and().
                logout().
                logoutUrl("/logout").
                logoutSuccessUrl("/").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID");


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.
                userDetailsService(this.boilSecurityDetailService).
                passwordEncoder(this.passwordEncoder);

    }
}
