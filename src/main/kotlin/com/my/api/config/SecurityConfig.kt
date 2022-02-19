package com.my.api.config

import com.my.api.security.JwtTokenAuthenticationFilter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "auth")
class SecurityConfig(
    var whiteList: List<String> = listOf(),
) : WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity) {
        http.csrf()
            .disable()

        http.cors()

        http.formLogin()
            .disable()

        http.authorizeRequests()
            .antMatchers(*whiteList.toTypedArray())
            .permitAll()
            .anyRequest()
            .authenticated()

        http.exceptionHandling()
            .authenticationEntryPoint(){
                _, response, _ ->
                response.sendRedirect("/denied")
            }
            .accessDeniedHandler{
                _, response, _ ->
                response.sendRedirect("/denied")
            }

        http.addFilterBefore(JwtTokenAuthenticationFilter(whiteList), UsernamePasswordAuthenticationFilter::class.java)
    }
}