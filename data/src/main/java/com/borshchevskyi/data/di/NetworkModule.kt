package com.borshchevskyi.data.di

import com.borshchevskyi.domaine.di.KoinModuleProvider
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.borshchevskyi.data.remote.MovieApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule : KoinModuleProvider {
    override fun get() = module {
        single { provideRetrofit(provideDefaultHttpClient(), get()) }
        single { UrlProvider() }
        single { provideMainApi(get()) }
    }

    private fun provideMainApi(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)
//  private fun provideAnyOtherApi(retrofit: Retrofit) = retrofit.create(Any::class.java)

    private fun provideRetrofit(client: OkHttpClient, urlProvider: UrlProvider) = Retrofit.Builder()
            .baseUrl(urlProvider.provideBaseUrl())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    private fun provideDefaultHttpClient() = OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .build()

    private fun provideLoggingInterceptor() =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
}
