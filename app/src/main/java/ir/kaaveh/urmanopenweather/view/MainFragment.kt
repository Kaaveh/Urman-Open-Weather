package ir.kaaveh.urmanopenweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ir.kaaveh.urmanopenweather.R
import ir.kaaveh.urmanopenweather.databinding.FragmentMainBinding
import ir.kaaveh.urmanopenweather.viewmodel.WeatherViewModel

class MainFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val weatherViewModel: WeatherViewModel by viewModels()
        weatherViewModel.weather.observe(viewLifecycleOwner, { newWeather ->
            binding.weather = newWeather.current
        })

        navController = Navigation.findNavController(binding.root)
        binding.btnFutureWeather.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_detailFragment)
        }

        return binding.root
    }
}