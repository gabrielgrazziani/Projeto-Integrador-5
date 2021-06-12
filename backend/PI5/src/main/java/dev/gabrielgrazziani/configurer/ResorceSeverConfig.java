package dev.gabrielgrazziani.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
public class ResorceSeverConfig extends ResourceServerConfigurerAdapter{

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
	public void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.antMatchers(HttpMethod.POST,"/cliente").permitAll()
		.antMatchers("/cliente/**","/cliente").hasRole("CLIENTE")
		.antMatchers("/funcionario/**","/funcionario").hasRole("FUNCIONARIO")
		.antMatchers("/pessoa/**","/pessoa").authenticated()
		.antMatchers(HttpMethod.GET, "/servico_produto/**","/servico_produto").permitAll()
		.antMatchers("/servico_produto/**","/servico_produto").hasRole("FUNCIONARIO")
		.anyRequest().authenticated()
		.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.csrf().disable()
			.cors();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}
}
