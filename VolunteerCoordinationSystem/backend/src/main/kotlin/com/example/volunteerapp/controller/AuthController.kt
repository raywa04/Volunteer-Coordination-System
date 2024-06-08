package com.example.volunteerapp.controller

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.http.HttpStatusCode

class AuthController {

    fun setupRoutes(routing: Routing) {
        routing {
            authenticate {
                get("/me") {
                    val principal = call.principal<JWTPrincipal>()
                    val username = principal!!.payload.getClaim("username").asString()
                    call.respond(mapOf("username" to username))
                }
            }

            post("/login") {
                val loginRequest = call.receive<LoginRequest>()
                // Here, implement login logic, validate credentials, generate JWT token, etc.
                call.respond(HttpStatusCode.OK, "Login successful")
            }

            post("/register") {
                val registerRequest = call.receive<RegisterRequest>()
                // Here, implement registration logic, save user to database, etc.
                call.respond(HttpStatusCode.Created, "User registered successfully")
            }
        }
    }
}

data class LoginRequest(val username: String, val password: String)
data class RegisterRequest(val username: String, val password: String, val role: String)
