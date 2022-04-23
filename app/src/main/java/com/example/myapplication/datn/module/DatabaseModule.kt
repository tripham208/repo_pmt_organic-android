package com.example.myapplication.datn.module
import android.content.Context
import com.example.myapplication.datn.database.AppDatabase
import com.example.myapplication.datn.model.dao.ProductDao
import com.example.myapplication.datn.model.dao.ProductTypeDao
import com.example.myapplication.datn.model.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao {
        return db.productDao()
    }
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }
    @Provides
    fun provideProductTypeDao(db: AppDatabase): ProductTypeDao {
        return db.productTypeDao()
    }
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext,CoroutineScope(SupervisorJob()))
    }
}