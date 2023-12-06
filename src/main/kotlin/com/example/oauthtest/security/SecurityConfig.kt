package com.example.oauthtest.security

import com.example.oauthtest.security.oauth.CustomAuthorizationRequestRepository
import com.example.oauthtest.security.oauth.OAuthLoginSuccessHandler
import com.example.oauthtest.security.oauth.OAuthUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val oAuthUserService: OAuthUserService,
    private val oAuthLoginSuccessHandler: OAuthLoginSuccessHandler,
    private val oAuthAuthorizationRequestRepository: CustomAuthorizationRequestRepository
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.formLogin { it.disable() }
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .oauth2Login { oauthConfig ->
                oauthConfig.redirectionEndpoint {
                    it.baseUri("/oauth2/*/callback")
                }.userInfoEndpoint {
                    it.userService(oAuthUserService)
                }.authorizationEndpoint {
                    it.authorizationRequestRepository(oAuthAuthorizationRequestRepository)
                }.successHandler(oAuthLoginSuccessHandler)
            }
            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfig = CorsConfiguration()

        corsConfig.addAllowedOriginPattern("*")
        corsConfig.addAllowedHeader("*")
        corsConfig.addAllowedMethod("*")
        corsConfig.allowCredentials = true

        val urlSource = UrlBasedCorsConfigurationSource()
        urlSource.registerCorsConfiguration("/**", corsConfig)
        return urlSource
    }
}