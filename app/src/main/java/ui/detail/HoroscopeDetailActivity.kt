package ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.ciberseguridadapp.databinding.ActivityHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()


        horoscopeDetailViewModel.getHoroscope(args.type.name)

    }

    private fun initUi() {
        initUiState()
    }

    //enganchar a un estado flow que esta en el viewModel
//generamos estados para que nuestro horoscopeDetail state nos devuelva solo uno
    private fun initUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        is HoroscopeDetailState.Error -> loadingState()
                        HoroscopeDetailState.Loading -> errorState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }

                }

            }

        }

    }

    private fun loadingState() {
        binding.ProgressBAR.isVisible = true
    }

    private fun errorState() {
        binding.ProgressBAR.isVisible = false

    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.ProgressBAR.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction
    }
}