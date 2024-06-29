package ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ciberseguridadapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ui.horoscope.adapter.HoroscopeAdapter

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    //Enganchamos el viewmodel a este fragment
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private lateinit var  horoscopeAdapter: HoroscopeAdapter

    //El Biding es un poco diferente en los Fragments
    private var _binding:FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        initList()
        initUIState()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter()

        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter  = horoscopeAdapter
        }
    }

    private fun initUIState() {
       //Coorutina enganchada al ciclo del vida del fragmento se usa esta coorutina en fragments
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeViewModel.horoscope.collect{
                    //CAMBIOS EN HOROSCOPE
                    horoscopeAdapter.updateList(it)
                }
            }

       }
    }

    //Este es el que crea la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
