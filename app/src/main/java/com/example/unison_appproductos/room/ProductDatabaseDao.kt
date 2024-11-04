package com.example.unison_appproductos.room

import androidx.room.*
import com.example.unison_appproductos.model.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: Producto)

    @Update
    suspend fun update(producto: Producto)

    @Delete
    suspend fun delete(producto: Producto)

    @Query("SELECT * FROM productos ORDER BY id ASC")
    fun getAllProducts(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE id = :id")
    suspend fun getProductById(id: Int): Producto?
}
