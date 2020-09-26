package ir.kaaveh.urmanopenweather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import ir.kaaveh.urmanopenweather.R
import ir.kaaveh.urmanopenweather.adapter.WeatherAdapter
import ir.kaaveh.urmanopenweather.databinding.FragmentDetailBinding
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

        val weatherViewModel: WeatherViewModel by viewModels()
        val weatherAdapter = WeatherAdapter()
        binding.weatherRecyclerview.adapter = weatherAdapter

        weatherViewModel.weather.observe(viewLifecycleOwner, {
            weatherAdapter.weathers = listOf(it.hourly[0], it.hourly[23])
        })

        return binding.root
    }
}