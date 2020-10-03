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
import ir.kaaveh.urmanopenweather.repository.WeatherRepository
import ir.kaaveh.urmanopenweather.repository.db.WeatherDatabase
import ir.kaaveh.urmanopenweather.repository.network.WeatherNetworkDataSource
import ir.kaaveh.urmanopenweather.viewmodel.WeatherViewModel
import ir.kaaveh.urmanopenweather.viewmodel.WeatherViewModelFactory

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = WeatherDatabase.getInstance(application)
        val weatherViewModelFactory =
            WeatherViewModelFactory(WeatherRepository(WeatherNetworkDataSource(), dataSource))
        val weatherViewModel: WeatherViewModel by viewModels { weatherViewModelFactory }

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