package ir.kaaveh.urmanopenweather.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.kaaveh.urmanopenweather.repository.db.WeatherDatabase
import ir.kaaveh.urmanopenweather.repository.network.WeatherNetworkDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = WeatherDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideNetworkDataSource() = WeatherNetworkDataSource()
}