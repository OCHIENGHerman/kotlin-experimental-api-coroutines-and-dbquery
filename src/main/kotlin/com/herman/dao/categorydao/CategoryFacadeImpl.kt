package com.herman.dao.categorydao

import com.herman.dao.DatabaseFactory.dbQuery
import com.herman.models.Categories
import com.herman.models.Category
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*


class CategoryFacadeImpl : CategoryFacade {
    private fun resultRowToCategory(row: ResultRow) = Category(
        id = row[Categories.id],
        name = row[Categories.name]
    )
    override suspend fun allCategories(): List<Category> = dbQuery {
        Categories.selectAll().map(::resultRowToCategory)
    }

    override suspend fun addNewCategory(name: String): Category? = dbQuery {
        val insertStatement = Categories.insert {
            it[Categories.name] = name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCategory)
    }

    override suspend fun deleteCategory(id: Int): Boolean = dbQuery {
        Categories.deleteWhere { Categories.id eq id } >0
    }

    override suspend fun patchCategory(id: Int, name: String): Boolean = dbQuery {
       Categories.update({Categories.id eq id}) {
           it[Categories.name] = name
       }
    }>0
}
val categorydao: CategoryFacade = CategoryFacadeImpl().apply {
    runBlocking {
        if (allCategories().isEmpty()) {
            addNewCategory("Politics")
        }
    }
}