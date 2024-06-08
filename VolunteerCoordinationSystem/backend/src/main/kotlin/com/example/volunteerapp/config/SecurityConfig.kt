package com.example.volunteerapp.config

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.jwt
import io.ktor.routing.Routing
import io.ktor.routing.route
import org.koin.dsl.module

fun Application.configureSecurity() {
    install(Authentication) {
        jwt {
            // JWT configuration
        }
    }
}

val securityModule = module {
    single { configureSecurity() }
}
