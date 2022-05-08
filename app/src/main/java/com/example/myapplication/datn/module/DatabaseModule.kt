package com.example.myapplication.datn.module

import android.content.Context
import com.example.myapplication.datn.database.AddressAPI
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.database.AppDatabase
import com.example.myapplication.datn.model.dao.*
import com.example.myapplication.datn.model.dao.address.AddressDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideOrderDao(db: AppDatabase): OrderDao {
        return db.orderDao()
    }

    @Provides
    fun provideDetailOrderDao(db: AppDatabase): DetailOrderDao {
        return db.detailOrderDao()
    }
    @Provides
    fun provideAddressDao(db: AppDatabase): AddressDao {
        return db.addressDao()
    }
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext, CoroutineScope(SupervisorJob()))
    }

    @Provides
    @Singleton
    fun provideAPI(): AppAPI {
        /**
         * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
         * full Kotlin compatibility.
         */
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        /**
         * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
         * object.
         */
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(AppAPI.BASE_URL)
            .build()


        return retrofit.create(AppAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAddressAPI(): AddressAPI {
        /**
         * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
         * full Kotlin compatibility.
         */
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        /**
         * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
         * object.
         */
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(AddressAPI.BASE_URL)
            .build()


        return retrofit.create(AddressAPI::class.java)
    }
}