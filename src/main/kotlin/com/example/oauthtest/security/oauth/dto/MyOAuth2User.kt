package com.example.oauthtest.security.oauth.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User

data class MyOAuth2User(
    val id: String,
    val provider: String,
    val nickname: String
) : OAuth2User {

    override fun getName(): String {
        return "$provider:$id"
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return mutableMapOf()
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    companion object {
        fun of(provider: String, userRequest: OAuth2UserRequest, originUser: OAuth2User): OAuth2User {
            return when (provider) {
                "KAKAO", "kakao" -> {
                    val profile = originUser.attributes["properties"] as Map<*, *>
                    val userNameAttributeName =
                        userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
                    val nickname = profile["nickname"] ?: ""

                    MyOAuth2User(
                        id = (originUser.attributes[userNameAttributeName] as Long).toString(),
                        provider = provider,
                        nickname = nickname as String,
                    )
                }
                else -> throw RuntimeException("지원하지 않는 OAuth Provider 입니다.")
            }
        }

    }
}