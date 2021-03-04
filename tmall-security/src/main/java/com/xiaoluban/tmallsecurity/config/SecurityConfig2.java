//package com.xiaoluban.tmallsecurity.config;
//
//import com.xiaoluban.tmallsecurity.config.component.JwtAuthenticationTokenFilter;
//import com.xiaoluban.tmallsecurity.config.component.RestAuthenticationEntryPoint;
//import com.xiaoluban.tmallsecurity.config.component.RestfulAccessDeniedHandler;
//import com.xiaoluban.tmallsecurity.utils.JwtTokenUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * @Author: txb
// * @Date: 20210124
// */
//@Configuration
////@EnableGlobalMethodSecurity(prePostEnabled=true) //TODO 不懂
//public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
//
//    private final static Logger log= LoggerFactory.getLogger(SecurityConfig2.class);
//
//
//    @Override
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return manager;
//    }
//
//
//
//    //密码编码器
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//
//    private String[] SWAGGER_WHITELIST = {
//            "/swagger-ui.html",
//            "/doc.html",
//            "/swagger-ui/*",
//            "/swagger-resources/**",
//            "/v2/api-docs",
//            "/v3/api-docs",
//            "/webjars/**"
//    };
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
//                .authorizeRequests();
//
//        //允许跨域请求的OPTIONS请求
//        registry.antMatchers(HttpMethod.OPTIONS)
//                .permitAll();
//        // 任何请求需要身份认证
//        registry.and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/dologin")
//                .usernameParameter("uname")
//                .passwordParameter("pwd")
//
//                // 关闭跨站请求防护及不使用session
//                .and()
//                .csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                // 自定义权限拒绝处理类
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler())
//                .authenticationEntryPoint(restAuthenticationEntryPoint())
//
//                // 自定义权限拦截器JWT过滤器
//                .and()
//                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////
////        http.authorizeRequests()
////
////                .antMatchers(SWAGGER_WHITELIST).permitAll()
////                // 任何请求需要身份认证
////                .and()
////                .authorizeRequests()
////
////                .anyRequest()
////                .authenticated()
////
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .loginProcessingUrl("/dologin")
////                .usernameParameter("uname")
////                .passwordParameter("pwd")
////
////                // 关闭跨站请求防护及不使用session
////                .and()
////                .csrf()
////                .disable()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                // 自定义权限拦截器JWT过滤器
////                .and()
////                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
////    }
//
//
//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Bean
//    public JwtTokenUtil jwtTokenUtil() {
//        return new JwtTokenUtil();
//    }
//
//    @Bean
//    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
//        return new RestfulAccessDeniedHandler();
//    }
//
//    @Bean
//    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
//        return new RestAuthenticationEntryPoint();
//    }
//}
