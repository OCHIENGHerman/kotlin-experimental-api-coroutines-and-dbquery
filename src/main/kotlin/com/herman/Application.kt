package com.herman

import com.herman.dao.DatabaseFactory
import io.ktor.server.application.*
import com.herman.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configurePaging()
    configureSerialization()
    DatabaseFactory.init()
}
