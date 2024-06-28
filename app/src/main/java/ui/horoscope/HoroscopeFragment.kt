package ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ciberseguridadapp.databinding.FragmentHoroscopeBinding


class HoroscopeFragment : Fragment() {

    //El Biding es un poco diferente en los Fragments
    private var _binding:FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!


    //Este es el que crea la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
