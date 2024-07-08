package data.providers

import android.util.Log
import data.providers.network.HoroscopeApiService
import data.providers.network.response.PredictionResponse
import domain.Repository
import domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {


    override suspend fun getPrediction(sign: String):PredictionModel? {
        //Llamar a Retrofit
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("high", "Ha ocurrido un error ${it.message}") }

        return null

    }

}