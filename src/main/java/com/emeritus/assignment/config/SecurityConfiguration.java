package com.emeritus.assignment.config;

import com.emeritus.assignment.filter.JwtFilter;
import com.emeritus.assignment.service.SchoolUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
    jsr250Enabled = true,
        securedEnabled = true
)
public class SecurityConfiguration {

   private final SchoolUserDetailsService schoolUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    public SecurityConfiguration(SchoolUserDetailsService schoolUserDetailsService) {
        this.schoolUserDetailsService = schoolUserDetailsService;

    }

    /*@Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
       return authenticationManagerBuilder
                .userDetailsService(schoolUserDetailsService)
                .passwordEncoder(passwordEncoder()).and().build();
    }*/





    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeRequests( auth -> auth
                        .antMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(schoolUserDetailsService)
                .headers(header-> header.frameOptions().sameOrigin())
                .httpBasic(withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .build();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
