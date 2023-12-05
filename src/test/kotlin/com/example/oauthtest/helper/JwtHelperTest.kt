package com.example.oauthtest.helper

import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.api.Test

class JwtHelperTest {

    private val jwtHelper = JwtHelper()

    @Test
    fun `generateAccessToken - 정상발행 테스트`() {
        shouldNotThrow<RuntimeException> {
            jwtHelper.generateAccessToken("KAKAO", "10")
        }
    }
}