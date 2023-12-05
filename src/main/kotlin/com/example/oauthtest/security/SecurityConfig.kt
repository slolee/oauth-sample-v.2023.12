package com.example.oauthtest.security

import com.example.oauthtest.security.oauth.OAuthLoginSuccessHandler
import com.example.oauthtest.security.oauth.OAuthUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val oauthUserService: OAuthUserService,
    private val oAuthLoginSuccessHandler: OAuthLoginSuccessHandler
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.formLogin { it.disable() }
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .oauth2Login { oauthConfig ->
                oauthConfig.redirectionEndpoint {
                    it.baseUri("/oauth2/*/callback")
                }
                oauthConfig.userInfoEndpoint {
                    it.userService(oauthUserService)
                }
                oauthConfig.successHandler(oAuthLoginSuccessHandler)
            }
            .build()
    }
}