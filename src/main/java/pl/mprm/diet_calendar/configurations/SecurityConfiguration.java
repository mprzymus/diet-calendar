package pl.mprm.diet_calendar.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static pl.mprm.diet_calendar.configurations.SecurityConfigurationConst.*;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("pass"))
                .roles(PATIENT_USER_ROLE)
                .and()
                .withUser("diet")
                .password(passwordEncoder.encode("pass"))
                .roles(DIETITIAN_USER_ROLE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/patient/**").hasAnyRole(PATIENT_USER_ROLE, DIETITIAN_USER_ROLE)
                .antMatchers("/dietician/**").hasRole(DIETITIAN_USER_ROLE)
                .and().formLogin();
    }
}
