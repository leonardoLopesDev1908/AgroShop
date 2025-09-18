package com.dailycodework.agroshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true, jsr250Enabled=true)
public class SecurityConfiguration {

    /*
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http) throws Exception {
                
      
                return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll();
                    authorize.requestMatchers("/login/**").permitAll();
<<<<<<< HEAD
                    authorize.anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/");
                })
                .build();
    }
=======
                    authorize.requestMatchers("/pedido/pesquisar").hasAuthority("GERENTE");
                    authorize.anyRequest().authenticated();
                })
                .formLogin(form -> 
                    form.loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/", true)    
                )
                Para impedir o uso de RequestCache(salvar o request do usuario):

                    RequestCache nullRequestCache = new NullRequestCache();
                    .requestCache((cache) -> cache
                        .requestCache(nullRequestCache)
                    )
                        
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger.ui/**",
            "/webjars/**"
        );
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
    */
}
