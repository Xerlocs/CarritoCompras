package cl.dv.carritocompras.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.dv.carritocompras.database.entities.QuoteEntity

@Dao
interface QuoteDao {
    @Query("SELECT * FROM item_table ORDER BY nombre DESC")
    fun getAllQuotes():List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes:List<QuoteEntity>)

    @Delete
    fun delete(user: QuoteEntity)

    @Update
    fun updateUsers(users: QuoteEntity)
}