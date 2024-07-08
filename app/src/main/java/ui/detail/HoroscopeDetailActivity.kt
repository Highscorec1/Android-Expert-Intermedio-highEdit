package ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavArgs
import androidx.navigation.navArgs
import com.example.ciberseguridadapp.R
import com.example.ciberseguridadapp.databinding.ActivityHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel:HoroscopeDetailViewModel by viewModels()

    private val args:HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()

    }

    private fun initUi() {
        initUiState()
    }
//enganchar a un estado flow que esta en el viewModel
//generamos estados para que nuestro horoscopeDetail state nos devuelva solo uno
    private fun initUiState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeDetailViewModel.state.collect{
                    when(it){
                        is HoroscopeDetailState.Error ->  loadingState()
                        HoroscopeDetailState.Loading -> errorState()
                        is HoroscopeDetailState.Success -> successState()
                    }

                }

            }

        }

    }

    private fun loadingState(){
        binding.ProgressBAR.isVisible = true
    }

    private fun errorState(){

    }

    private fun successState(){

    }
}
