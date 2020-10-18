package ir.kaaveh.urmanopenweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ir.kaaveh.urmanopenweather.R
import ir.kaaveh.urmanopenweather.adapter.WeatherAdapter
import ir.kaaveh.urmanopenweather.databinding.FragmentDetailBinding
import ir.kaaveh.urmanopenweather.repository.WeatherRepository
import ir.kaaveh.urmanopenweather.repository.db.WeatherDatabase
import ir.kaaveh.urmanopenweather.repository.network.WeatherNetworkDataSource
import ir.kaaveh.urmanopenweather.viewmodel.WeatherViewModel

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = WeatherDatabase.getInstance(application)
        val weatherViewModelFactory =
            WeatherViewModelFactory(WeatherRepository(WeatherNetworkDataSource(), dataSource))
        val weatherViewModel: WeatherViewModel by viewModels { weatherViewModelFactory }

        val weatherAdapter = WeatherAdapter()
        binding.weatherRecyclerview.adapter = weatherAdapter

        weatherViewModel.forecastWeather.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                weatherAdapter.weathers = listOf(it[0], it[23])
            }
        })

        return binding.root
    }
}