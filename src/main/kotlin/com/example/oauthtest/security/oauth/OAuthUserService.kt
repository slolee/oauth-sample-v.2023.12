package com.example.oauthtest.security.oauth

import com.example.oauthtest.security.oauth.dto.MyOAuth2User
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OAuthUserService : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val originUser = super.loadUser(userRequest)

        val provider = userRequest.clientRegistration.clientName
        return MyOAuth2User.of(provider, userRequest, originUser)
    }
}