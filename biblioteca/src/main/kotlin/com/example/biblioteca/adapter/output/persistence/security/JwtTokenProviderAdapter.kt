package com.example.biblioteca.adapter.output.persistence.security

import com.example.biblioteca.domain.user.entity.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JwtTokenProviderAdapter(
    @Value("\${api.security.token.secret}") // Pega do application.yml
    private val secretKey: String,

    @Value("\${api.security.token.expiration}") // Configurar tempo no yml
    private val tokenExpiration: Long = 86400000 // Padrão em 24h
) : TokenProviderPort{


    override fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.email.value)
            .claim("id", user.id.toString())
            .claim("roles", user.roles)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + tokenExpiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS512)
            .compact()
    }

    override fun validateToken(token: String): Boolean {
        return try{
            Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
            true
        }catch (e: Exception){
            false
        }
    }

    override fun extractToken(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }


    private fun getSignInKey(): Key {
        // Transforma a string secreta em bytes para o algoritmo HMAC
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }


    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }


}