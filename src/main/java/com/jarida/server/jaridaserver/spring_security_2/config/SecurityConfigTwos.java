package com.jarida.server.jaridaserver.spring_security_2.config;

import com.jarida.server.jaridaserver.spring_security_2.security.CustomUserDetailsServiceTwos;
import com.jarida.server.jaridaserver.spring_security_2.security.JwtAuthenticationEntryPointTwos;
import com.jarida.server.jaridaserver.spring_security_2.security.JwtAuthenticationFilterTwos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigTwos extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsServiceTwos userDetailsServiceTwos;

   @Autowired
   private JwtAuthenticationEntryPointTwos authenticationEntryPointTwos;

    @Bean
    public JwtAuthenticationFilterTwos jwtAuthenticationFilterTwos(){
        return  new JwtAuthenticationFilterTwos();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointTwos)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/swagger/api").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/api/v2/**").permitAll()
                .antMatchers("/api/v3/signin").permitAll()
                .antMatchers("/api/v3/signup").permitAll()
                .antMatchers("/api/v3/login").permitAll()
                .antMatchers("/api/v3/register").permitAll()
                .antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui/index.html/").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger-resources").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/configuration/security").permitAll()
                .antMatchers("/swagger-resources/configuration/ui").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilterTwos(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceTwos)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //    @Override
    //    @Bean
    //    protected UserDetailsService userDetailsService() {
    //        UserDetails ramesh = User.builder().username("ramesh").password(passwordEncoder()
    //                .encode("password")).roles("USER").build();
    //        UserDetails admin = User.builder().username("admin").password(passwordEncoder()
    //                .encode("admin")).roles("ADMIN").build();
    //        return new InMemoryUserDetailsManager(ramesh, admin);
    //    }





}
