package com.example.lafyuu.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.lafyuu.api.ProductServices
import com.example.lafyuu.room.AppDatabase
import com.example.lafyuu.room.BasketDAO
import com.example.lafyuu.room.BookmarkDAO
import com.example.lafyuu.room.MigrationHelper
import com.example.lafyuu.room.ProductsDAO
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton

    fun provideFireBase():FirebaseAuth{
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun providesServices(): ProductServices {
        val retrofit= Retrofit.Builder().baseUrl("https://api.escuelajs.co/api/v1/").
        addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(ProductServices::class.java)
    }


    @Singleton
    @Provides
      fun providesSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("localSP",Context.MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }



//    @Provides
//    @Singleton
//    fun provideProductsDAO(database: AppDatabase): ProductsDAO {
//        return database.productsDAO()
//    }


    @Provides
    fun provideProductsDAO(db: AppDatabase): ProductsDAO = db.productsDAO()

    @Provides
    fun provideBasketDAO(db: AppDatabase): BasketDAO = db.basketDAO()

    @Provides
    fun provideBookmarkDAO(db: AppDatabase): BookmarkDAO = db.bookmarkDAO()

    }












