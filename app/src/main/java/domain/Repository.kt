package domain

import domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel?
}