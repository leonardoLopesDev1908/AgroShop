package com.dailycodework.agroshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain( 
                    HttpSecurity http) throws Exception {
        
        return http 
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll();
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/");
                })
                .build();
    }
}
