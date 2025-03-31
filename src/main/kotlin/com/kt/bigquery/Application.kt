package com.kt.bigquery

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import com.kt.bigquery.routes.configureRouting
import com.kt.bigquery.config.AppConfig

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson()
    }
    
    val config = AppConfig.load(environment.config)
    configureRouting(config)
}