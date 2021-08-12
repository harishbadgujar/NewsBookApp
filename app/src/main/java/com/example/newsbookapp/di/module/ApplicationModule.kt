package com.example.newsbookapp.di.module


import com.example.newsbookapp.BuildConfig
import com.example.newsbookapp.network.apiservice.BooksService
import com.example.newsbookapp.network.apiservice.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Named("newsUrl")
    fun provideBaseUrl() = "https://newsapi.org/v2/"

    @Provides
    @Named("booksUrl")
    fun provideBooksUrl() = "https://www.googleapis.com/books/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    } else OkHttpClient
            .Builder()
            .build()

    @Provides
    @Singleton
    @Named("newsRetrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient, @Named("newsUrl") BASE_URL: String): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideApiService(@Named("newsRetrofit") retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Provides
    @Singleton
    @Named("booksRetrofit")
    fun provideBooksRetrofit(okHttpClient: OkHttpClient, @Named("booksUrl") BOOKS_URL: String): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(BOOKS_URL)
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideBooksService(@Named("booksRetrofit") booksRetrofit: Retrofit): BooksService = booksRetrofit.create(BooksService::class.java)
}