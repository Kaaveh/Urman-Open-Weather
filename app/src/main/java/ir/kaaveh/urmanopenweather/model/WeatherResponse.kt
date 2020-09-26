package ir.kaaveh.urmanopenweather.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val WEATHER_ID = 0

@Entity(tableName = "weather_info")
data class WeatherResponse(
    @Embedded(prefix = "current_weather_")
    val current: Weather,

    @Embedded(prefix = "future_weather_")
    val hourly: List<Weather>
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = WEATHER_ID
}