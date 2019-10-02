package bd.analytics.vkr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class Security extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService(String login) {
        /*User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("pass").roles("USER").build());
        manager.createUser(users.username("admin").password("pass").roles("ADMIN").build());
        return manager;*/
        return new EmployeeDetailService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

}