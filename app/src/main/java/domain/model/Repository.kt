package domain.model

interface Repository {
    suspend fun getPrediction(string: String)
}