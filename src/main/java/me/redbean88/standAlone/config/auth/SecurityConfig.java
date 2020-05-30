package me.redbean88.standAlone.config.auth;

import lombok.RequiredArgsConstructor;
import me.redbean88.standAlone.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity      //시큐리티 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 이용을 위한 옵션
                .and()
                    .authorizeRequests()    //권한 설정 시작
                    .antMatchers("/","/css/**","/image/**","/js/**","/h2-console/**").permitAll()   //전체 허용
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //USER 권한만 허용
                    .anyRequest().authenticated()   //나머지 URL(인증된 사용자 허용 )
                .and()
                    .logout()//로그아웃 설정 시작
                        .logoutSuccessUrl("/")  //로그아웃 URL
                .and()
                    .oauth2Login()  //OAuth2 설정 시작
                        .userInfoEndpoint() //로그인 성공 이후 설정
                            .userService(customOAuth2UserService); //로그인 성공후 구현체
    }
}
