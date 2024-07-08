package data.providers.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.providers.RepositoryImpl
import domain.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
      return  Retrofit
            .Builder()
            .baseUrl("https://newastro.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService{
        return retrofit.create(HoroscopeApiService::class.java)
    }

    @Provides
    fun provideRespository(apiService: HoroscopeApiService): Repository{
        return RepositoryImpl(apiService)
    }
}