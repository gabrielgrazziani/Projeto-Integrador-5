package dev.gabrielgrazziani.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.gabrielgrazziani.service.PessoaDetailService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private final PessoaDetailService detailService;
	
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
			.antMatchers("/cliente/**","/cliente").hasRole("CLIENTE")
			.antMatchers("/funcionario/**","/funcionario").hasRole("FUNCIONARIO")
			.antMatchers("/pessoa/**","/pessoa").authenticated()
			.antMatchers(HttpMethod.GET, "/servico_produto/**","/servico_produto").permitAll()
			.antMatchers("/servico_produto/**","/servico_produto").hasRole("FUNCIONARIO")
			.anyRequest().authenticated()
			.and().logout()
			.and().userDetailsService(detailService)
			.httpBasic()
			.and().cors();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailService)
			.passwordEncoder(encoder());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
