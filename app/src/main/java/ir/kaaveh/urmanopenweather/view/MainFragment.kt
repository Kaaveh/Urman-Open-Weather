package ir.kaaveh.urmanopenweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ir.kaaveh.urmanopenweather.R
import ir.kaaveh.urmanopenweather.databinding.FragmentMainBinding
import ir.kaaveh.urmanopenweather.viewmodel.WeatherViewModel

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val weatherViewModel: WeatherViewModel by viewModels()

        weatherViewModel.currentWeather.observe(viewLifecycleOwner, { newWeather ->
            binding.weather = newWeather
            binding.groupLoading.visibility = View.GONE
        })

        binding.btnFutureWeather.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment())
        }

        return binding.root
    }
}