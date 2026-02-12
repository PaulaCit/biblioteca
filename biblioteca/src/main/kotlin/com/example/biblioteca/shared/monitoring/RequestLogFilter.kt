package com.example.biblioteca.shared.monitoring

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.UUID
import jakarta.servlet.Filter


@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // Executa antes de tudo!
class RequestLogFilter : Filter {
    private val logger = org.slf4j.LoggerFactory.getLogger(RequestLogFilter::class.java)

    override  fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain){


        val req = request as HttpServletRequest
        val res = response as HttpServletResponse

        val startTime = System.currentTimeMillis()
        logger.info("Iniciando requisição: ${req.method} ${req.requestURI}")

        // 1. Tenta pegar o ID que veio do Frontend ou gera um novo
        val correlationId = req.getHeader(CORRELATION_ID)?: UUID.randomUUID().toString()


        try {
            // 2. coloca no "Contexto" do log (MDC)
            // Agora TODO log.info() vai ter esse ID automaticamente
            MDC.put("correlationId", correlationId)

            // 3. Devolve o ID no Header da resposta (para o Front saber qual foi o ID)
            res.addHeader(CORRELATION_ID, correlationId)

            // Segue o fluxo(vai pro Controller)
            chain.doFilter(request, response)
        }finally {
            val duration = System.currentTimeMillis() - startTime
            logger.info("Finalizando requisição: Status ${res.status} - Tempo: ${duration}ms")
            // 4. Limpa  o contexto para não sujar a próxima requisição (Thread Pool é reutilizado!)
            MDC.clear()
        }
    }

    companion object {
        const val CORRELATION_ID = "X-Correlation-ID"
    }


}