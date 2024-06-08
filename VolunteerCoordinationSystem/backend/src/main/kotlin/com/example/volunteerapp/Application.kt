package com.example.volunteerapp

import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(DefaultHeaders)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(StatusPages) {
        exception<Throwable> { cause ->
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to (cause.localizedMessage ?: "unknown")))
        }
    }

    install(Koin) {
        modules(appModule)
    }

    Database.connect(
        url = environment.config.property("database.url").getString(),
        driver = environment.config.property("database.driver").getString(),
        user = environment.config.property("database.user").getString(),
        password = environment.config.property("database.password").getString()
    )

    val authController by inject<AuthController>()
    val volunteerController by inject<VolunteerController>()
    val organizationController by inject<OrganizationController>()
    val eventController by inject<EventController>()

    install(Routing) {
        authController.setupRoutes(this)
        volunteerController.setupRoutes(this)
        organizationController.setupRoutes(this)
        eventController.setupRoutes(this)
    }
}
