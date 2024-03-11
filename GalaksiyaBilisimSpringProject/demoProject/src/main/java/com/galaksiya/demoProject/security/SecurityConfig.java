package com.galaksiya.demoProject.security;

import com.galaksiya.demoProject.business.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(IUserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/product/find/**").hasAnyRole("MANAGER", "CUSTOMER")
                                .requestMatchers("/product/all").hasAnyRole("MANAGER", "CUSTOMER")
                                .requestMatchers("/product/**").hasAnyRole("MANAGER")
                                .requestMatchers("/order/find/myOrders").hasAnyRole("MANAGER", "CUSTOMER")
                                .requestMatchers("/order/find/**").hasAnyRole("MANAGER")
                                .requestMatchers("/order/update").hasAnyRole("MANAGER")
                                .requestMatchers("/order/**").hasAnyRole("MANAGER","CUSTOMER")
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .formLogin(form ->
                        form
                                .successHandler(new SimpleUrlAuthenticationSuccessHandler("/swagger-ui/index.html"))
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                );

        return http.build();
    }
}
