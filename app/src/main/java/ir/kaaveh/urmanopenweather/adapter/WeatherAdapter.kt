package ir.kaaveh.urmanopenweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.kaaveh.urmanopenweather.R
import ir.kaaveh.urmanopenweather.databinding.ItemRecyclerviewWeatherBinding
import ir.kaaveh.urmanopenweather.model.Weather

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    var weathers: List<Weather>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recyclerview_weather,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = weathers?.size ?: 0

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.weather = weathers!![position]
    }

    class WeatherViewHolder(val binding: ItemRecyclerviewWeatherBinding) :
        RecyclerView.ViewHolder(binding.root)
}