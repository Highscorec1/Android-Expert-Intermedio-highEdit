package data.providers.network

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import data.providers.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {

    @GET ("/{sign}")

    suspend fun getHoroscope(@Path("sing") sign:String): PredictionResponse
}