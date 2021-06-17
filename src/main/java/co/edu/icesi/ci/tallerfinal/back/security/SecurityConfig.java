package co.edu.icesi.ci.tallerfinal.back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//		/*httpSecurity.authorizeRequests().antMatchers("/**").authenticated().anyRequest().permitAll().and().httpBasic().and().logout()
//				.invalidateHttpSession(true).clearAuthentication(true)
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
//				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);*/
//
//
		httpSecurity.authorizeRequests()
        .antMatchers("/front/login**").permitAll()
        .antMatchers("/h2/**").permitAll()
//        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/front/measurements/showFromCheckMeasur/**").hasAnyRole("admin", "operator")
        .antMatchers("/front/checkmeasures/showFromMeasures/**").hasAnyRole("admin", "operator")
        .antMatchers("/front/measurements/**").hasRole("admin")
        .antMatchers("/front/phycheckups/**").hasRole("operator")
        .antMatchers("/front/visits/**").hasRole("operator")
        .antMatchers("/front/checkmeasures/**").hasRole("operator")
        .antMatchers("/front/**").authenticated().anyRequest().permitAll()
        .and()
            .formLogin().loginPage("/front/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
        .and().logout().invalidateHttpSession(true).clearAuthentication(true)
        .permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}


//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
////
////		/*httpSecurity.authorizeRequests().antMatchers("/**").authenticated().anyRequest().permitAll().and().httpBasic().and().logout()
////				.invalidateHttpSession(true).clearAuthentication(true)
////				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
////				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);*/
////
////
//
//
//		httpSecurity.authorizeRequests()
//				.antMatchers("/**").permitAll().and().csrf().disable();
//	}

}