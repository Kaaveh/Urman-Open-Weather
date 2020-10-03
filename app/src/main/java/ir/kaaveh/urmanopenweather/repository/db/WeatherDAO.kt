package ir.kaaveh.urmanopenweather.repository.db;


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.kaaveh.urmanopenweather.model.Weather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weathers: List<Weather>)

    @Query("SELECT * FROM weather_info WHERE id = 1")
    fun getCurrentWeather(): LiveData<Weather>

    @Query("SELECT * FROM weather_info WHERE id > 1")
    fun getAllWeatherForecast(): LiveData<List<Weather>>
}