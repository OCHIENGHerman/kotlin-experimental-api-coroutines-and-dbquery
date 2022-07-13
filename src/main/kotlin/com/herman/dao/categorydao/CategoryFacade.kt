package com.herman.dao.categorydao

import com.herman.models.Category

interface CategoryFacade {
    suspend fun allCategories(): List<Category>
    suspend fun addNewCategory(name: String): Category?
    suspend fun deleteCategory(id: Int): Boolean
    suspend fun patchCategory(id: Int, name: String): Boolean
}