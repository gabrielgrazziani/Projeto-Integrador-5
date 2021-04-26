package dev.gabrielgrazziani.configurer;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private static final String[] AUTH_WHITELIST = {
            "/",
			// -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui/**",
            "/csrf"
    };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(AUTH_WHITELIST).permitAll()
			.antMatchers(HttpMethod.POST,"/cliente").permitAll()
			.antMatchers("/cliente/**").hasRole("CLIENTE")
			.antMatchers("/funcionario/**","/funcionario").hasRole("FUNCIONARIO")
			.antMatchers("/pessoa/**","/pessoa").authenticated()
			.antMatchers("/servico_produto/**","/servico_produto").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().logout();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser("cliente")
			.password(encoder.encode("cliente"))
			.roles("CLIENTE")
		.and()
			.withUser("funcionario")
			.password(encoder.encode("funcionario"))
			.roles("FUNCIONARIO");
	}
}
