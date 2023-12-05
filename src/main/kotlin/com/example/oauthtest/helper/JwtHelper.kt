package com.example.oauthtest.helper

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtHelper {

    private val key: Key = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray())

    fun generateAccessToken(provider: String, id: String): String {
        return Jwts.builder()
            .setHeaderParam("typ", "ACCESS")
            .setSubject(id)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRES_IN))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    companion object {
        private const val SECRET_KEY: String =
            "billimDuatjgkrWkdguswjd#djfakskrlfrpgodigksmsrjdi1234567890billimDuatjgkrWkdguswjd#djfakskrlfrpgodigksmsrjdi1234567890"
        private const val ACCESS_TOKEN_EXPIRES_IN: Int = 1000 * 60 * 60 * 24
    }
}