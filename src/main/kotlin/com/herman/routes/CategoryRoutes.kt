package com.herman.routes

import com.herman.dao.categorydao.categorydao
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.allCategories() {
    get("/categories") {
        call.respond(mapOf("categories" to categorydao.allCategories()))
    }
}

fun Route.addNewCategory() {
    post("/category") {
        val formParameters = call.receiveParameters()
        val name = formParameters.getOrFail("name")
        categorydao.addNewCategory(name)
        call.respondRedirect("/categories")
    }
}

fun Route.deleteCategory() {
    delete("/category/{id}") {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        categorydao.deleteCategory(id)
        call.respondRedirect("/categories")
    }
}

fun Route.patchCategory() {
    patch("/category/{id}") {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        val formParameters = call.receiveParameters()
        val name = formParameters.getOrFail("name")
        categorydao.patchCategory(id, name)
        call.respondRedirect("/categories")
    }
}