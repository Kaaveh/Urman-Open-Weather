package ir.kaaveh.urmanopenweather.repository.db;


import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query
import ir.kaaveh.urmanopenweather.model.WeatherResponse

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: WeatherResponse)

    @Query("SELECT * FROM weather_info")
    fun getWeather(): LiveData<WeatherResponse>
}