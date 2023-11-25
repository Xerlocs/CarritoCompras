package cl.dv.carritocompras.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cl.dv.carritocompras.database.entities.QuoteEntity

@Dao
interface QuoteDao {
    @Query("SELECT * FROM item_table")
    fun getAllQuotes():List<QuoteEntity>

    @Query("SELECT * FROM item_table ORDER BY nombre")
    fun getOrderQuotes():List<QuoteEntity>

    @Query("SELECT * FROM item_table ORDER BY tipo")
    fun getOrder2Quotes():List<QuoteEntity>

    @Query("SELECT * FROM item_table ORDER BY cantidad")
    fun getOrder3Quotes():List<QuoteEntity>

    @Query("SELECT * FROM item_table WHERE nombre LIKE :searchQuery")
    fun getSearchQuotes(searchQuery: String):List<QuoteEntity>

    @Insert
    fun insertAll(vararg quotes: QuoteEntity)

    @Delete
    fun delete(user: QuoteEntity)

    @Update
    fun updateUsers(users: QuoteEntity)
}