package com.example.oauthtest.security.oauth

import com.example.oauthtest.helper.JwtHelper
import com.example.oauthtest.security.oauth.dto.MyOAuth2User
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuthLoginSuccessHandler(
    private val jwtHelper: JwtHelper
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oauthUser = authentication.principal as MyOAuth2User
        println("LOGIN SUCCESS! (Nickname: ${oauthUser.nickname})")

        val accessToken = jwtHelper.generateAccessToken(oauthUser.provider, oauthUser.id)
        response.contentType = MediaType.APPLICATION_JSON.toString()
        response.writer.write(accessToken)
    }
}