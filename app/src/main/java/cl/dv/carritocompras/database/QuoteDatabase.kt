package cl.dv.carritocompras.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import cl.dv.carritocompras.database.dao.QuoteDao
import cl.dv.carritocompras.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase:RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}