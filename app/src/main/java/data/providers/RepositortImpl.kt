package data.providers

import data.providers.network.HoroscopeApiService
import domain.model.Repository
import retrofit2.Retrofit
import javax.inject.Inject

class RepositortImpl @Inject constructor(private val apiService: HoroscopeApiService) :Repository {


    override suspend fun getPrediction(string: String) {
        //Llamar a Retrofit


    }

}