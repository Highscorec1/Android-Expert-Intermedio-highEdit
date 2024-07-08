package ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.usecae.GetPrediction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPrediction: GetPrediction):ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state:StateFlow<HoroscopeDetailState> = _state

    fun getHoroscope(sign:String) {
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            val result = withContext((Dispatchers.IO)){getPrediction(sign)} //hilo secundario

            if(result !=null){
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sing)
            }else {

                _state.value = HoroscopeDetailState.Error("Ha ocurrido un errror, intentelo mas tarde")
            }
            //hilo principal
        }
    }

}