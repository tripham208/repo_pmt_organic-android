package com.example.myapplication.datn.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.datn.model.dao.ProductDao
import com.example.myapplication.datn.model.dao.ProductTypeDao
import com.example.myapplication.datn.model.dao.UserDao
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.model.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [User::class, Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun productTypeDao(): ProductTypeDao

    class WordDatabaseCallback(
        private val scope: CoroutineScope,
        private val context: Context
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    /*val itemDao = database.itemDao()
                    val json = readJsonFromFileAssets(fileName)
                    if (json != null) {
                        insertData(json, itemDao)
                    }*/
                }
            }

        }
        /*
        private fun readJsonFromFileAssets(fileName: String): String? {
            var json: String? = null
            try {
                val inputStream: InputStream = context.applicationContext.assets.open(fileName)
                json = inputStream.bufferedReader().use { it.readText() }
                return json
            } catch (e: Exception) {
                e.message?.let { Logger.e(it) }
            }
            return json
        }

        private suspend fun insertData(json: String, expenseDao: ExpenseDao) {
            val arr = JSONArray(json)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                expenseDao.insert(
                    Expense(
                        uid = null,
                        name = obj.getString("name"),
                        value = obj.getInt("value")
                    )
                )
            }
        }*/
    }


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        const val nameDB = "pmt_organic"
        const val fileName = "Item.json"

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    nameDB
                )
                    .addCallback(WordDatabaseCallback(scope, context))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}