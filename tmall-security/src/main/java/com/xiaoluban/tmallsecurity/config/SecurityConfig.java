package com.xiaoluban.tmallsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: txb
 * @Date: 20210124
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger log= LoggerFactory.getLogger(SecurityConfig.class);


    @Override
    @Bean
    public UserDetailsService userDetailsService(){

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return manager;
    }


    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


//    private String[] STATIC_WHITELIST = {
//            //swagger
//            "/swagger-ui.html",
//            "/doc.html",
//            "/swagger-ui/*",
//            "/swagger-resources/**",
//            "/v2/api-docs",
//            "/v3/api-docs",
//            "/webjars/**",
//            //静态资源
//            "/static/css/**",
//            "/static/fonts/**",
//            "/static/image/**",
//            "/static/js/**",
//            "/static/mycss/**",
//            "/static/myjs/**",
//            //另一种写法
////            "/**/*.js",
////            "/**/*.css",
////            "/**/*.jpg"
//    };


    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        List<String> whiteList=ignoreUrlsConfig().getUrls();
        String[] urls=new String[whiteList.size()];
        for(int i=0;i<whiteList.size();i++){
            urls[i]=whiteList.get(i);
        }
//        String[] STATIC_WHITELIST= (String[]) whiteList.toArray();

        http.authorizeRequests()

                //静态资源
                .antMatchers(urls).permitAll()

                 //开发测试放开
//                .antMatchers("/product/**").permitAll()
//                .antMatchers("/order/**").permitAll()
//                .antMatchers("/ums/**").permitAll()

                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/db/**").hasAnyRole("admin","user")
                .antMatchers("/user/**").access("hasAnyRole('admin','user')")

                //剩下的其他路径请求验证之后就可以访问
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/dologin")
                .usernameParameter("uname")
                .passwordParameter("pwd")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        RequestCache requestCache=new HttpSessionRequestCache();
                        SavedRequest savedRequest=requestCache.getRequest(request,response);

                        String redirectUrl= null;
                        try {
                            redirectUrl = savedRequest.getRedirectUrl();
                        } catch (Exception e) {
                            response.sendRedirect("/index");
                            return;
                        }

                        log.info("初始访问路径："+redirectUrl);
                        response.sendRedirect(redirectUrl);

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter pw = response.getWriter();
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("status", 401);
                        if (exception instanceof LockedException) {
                            map.put("msg", "账户被锁定，登陆失败！");
                        } else if (exception instanceof BadCredentialsException) {
                            map.put("msg", "账户或者密码错误，登陆失败！");
                        } else if (exception instanceof DisabledException) {
                            map.put("msg", "账户被禁用，登陆失败！");
                        } else if (exception instanceof AccountExpiredException) {
                            map.put("msg", "账户已过期，登陆失败！");
                        } else if (exception instanceof CredentialsExpiredException) {
                            map.put("msg", "密码已过期，登陆失败！");
                        } else {
                            map.put("msg", "登陆失败！");
                        }
                        pw.write(new ObjectMapper().writeValueAsString(map));
                        pw.flush();
                        pw.close();
                    }
                })
                .permitAll()
                .and()
                .cors()
                //不处理跨域
                .and()
                .csrf().disable();

    }
}
