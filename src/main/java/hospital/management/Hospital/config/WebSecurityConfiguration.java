package hospital.management.Hospital.config;

import hospital.management.Hospital.service.UserSessionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    private MyCorsFilter myCorsFilter;

    @Autowired
    public WebSecurityConfiguration(@Lazy AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                               @Qualifier("userSessionManagementService") UserSessionManagementService userSessionManagementService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userSessionManagementService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/api/register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(myCorsFilter, ChannelProcessingFilter.class)
                    .cors()
                .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
//                    .antMatchers(HttpMethod.OPTIONS, "*/**").permitAll()
                    .antMatchers( "/api/login").permitAll()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers( HttpMethod.POST,"/api/drug/**").hasAuthority("admin")
                    .antMatchers( HttpMethod.PUT,"/api/drug/**").hasAuthority("admin")
                    .antMatchers( HttpMethod.DELETE,"/api/drug/**").hasAuthority("admin")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/api/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .failureHandler((req, res, e) -> sendError(res, 401))
                    .successHandler((req, res, a) -> res.setStatus(200)).and()
                    .exceptionHandling()
                    .accessDeniedHandler((req, res, e) -> sendError(res, 403))
                    .authenticationEntryPoint((req, res, e) -> sendError(res, 401))
                .and()
                    .logout()
                    .logoutUrl("/api/logout")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler((req, res, a) -> res.setStatus(200));
        http.sessionManagement().invalidSessionUrl("/");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private void sendError(HttpServletResponse response, int code) {
        response.setStatus(code);
        response.setHeader("Content-Type", "text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.print("Invalid login");
        } catch (IOException ignored) {
        }
    }
}
