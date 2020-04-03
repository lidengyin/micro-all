package cn.hctech2006.micro.microuaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置获取基本用户信息和用户权限的信息方法类userDetailService
        //设置密码对比策略，采用BCrypt加密算法
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    //登录认证过程时委托给AuthenticationManager完成的，Authentication提供了
    //一个默认的实现ProviderManager,而ProviderManager又将验证委托给了AuthenticationProvider
    //DaoAuthenticationProvider继承了AuthenticationProvider的抽象实现，AbstractUserdetailsAuthenticationProvider
    //完成了从DAO方式获取验证需要的用户信息，
    //对于我们一般用的DaoAuthenticationProvider是由UserDetailsService专门获取验证信息的
    //userDetailsSerrvice接口只有一个方法，loadUserByUsername(String username),一般需要我们实现此接口方法
    //根据用户名加载登录认证和访问授权所需要的信息，并返回一个UserDetils的实现类，
    //后面登录认证和访问授权搜需要用到此种的信息
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * URL访问控制
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // web jars
                .antMatchers("/webjars/**").permitAll()
                // 查看SQL监控（druid）
                .antMatchers("/druid/**").permitAll()
                // 首页和登录页面
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()

                .antMatchers("/upload.html").permitAll()
                .antMatchers("/templates/index.html").permitAll()
                // swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                // 验证码
                .antMatchers("/kaptcha").permitAll()
                .antMatchers("/qrcode/**").permitAll()
                // 服务监控
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/register").permitAll()

                .antMatchers("/imgUpload").permitAll()
                .antMatchers("/videoUpload").permitAll()
                .antMatchers("/fileUpload").permitAll()
                //.antMatchers("/upload/apk").permitAll()
                .antMatchers("/ueditor").permitAll()
                .antMatchers("/content").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();
        http.headers().frameOptions().disable();
    }
}
