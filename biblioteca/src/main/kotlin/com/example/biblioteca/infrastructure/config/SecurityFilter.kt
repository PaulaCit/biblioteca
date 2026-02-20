package com.example.biblioteca.infrastructure.config

import com.example.biblioteca.application.port.out.user.TokenProviderPort
import com.example.biblioteca.application.port.out.user.UserRepositoryPort
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    private val tokenProvider: TokenProviderPort,

    private val userRepository: UserRepositoryPort
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recoverToken(request)

        if (token != null && tokenProvider.validateToken(token)) {

            val login = tokenProvider.extractToken(token)

            val user = userRepository.findByEmail(login)

            if (user != null) {

                val authorities = emptyList<SimpleGrantedAuthority>()

                val authentication = UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    authorities
                )

                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null
        }
        return authHeader.substring(7)
    }
}