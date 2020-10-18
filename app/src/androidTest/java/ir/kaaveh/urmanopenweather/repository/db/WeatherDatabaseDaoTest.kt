package ir.kaaveh.urmanopenweather.repository.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import ir.kaaveh.urmanopenweather.getOrAwaitValue
import ir.kaaveh.urmanopenweather.model.Weather
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class WeatherDatabaseDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: WeatherDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        ).allowMainThreadQueries()
            .build()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertWeather() = runBlockingTest {
        val weather = Weather(temp = 32.0)
        database.weatherDao.insert(listOf(weather))

        val currentWeather = database.weatherDao.getCurrentWeather().getOrAwaitValue()
        assertThat(currentWeather).isEqualTo(weather)
    }

    @Test
    fun getAllWeatherForecast() = runBlockingTest {
        val weathers = listOf(
            Weather(id = 1, temp = 32.0),
            Weather(id = 2, temp = 30.0),
            Weather(id = 3, temp = 28.0),
        )
        database.weatherDao.insert(weathers)

        val forecastWeather = database.weatherDao.getAllWeatherForecast().getOrAwaitValue()
        assertThat(forecastWeather).isEqualTo(listOf(
            weathers[1],
            weathers[2]
        ))
    }
}