package domain.usecae

import domain.Repository
import javax.inject.Inject

class GetPrediction @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(sign:String) =  repository.getPrediction(sign)


}