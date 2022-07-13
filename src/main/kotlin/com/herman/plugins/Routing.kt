package com.herman.plugins

import com.herman.routes.addNewCategory
import com.herman.routes.allCategories
import com.herman.routes.deleteCategory
import com.herman.routes.patchCategory
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        allCategories()
        addNewCategory()
        deleteCategory()
        patchCategory()
    }
}
