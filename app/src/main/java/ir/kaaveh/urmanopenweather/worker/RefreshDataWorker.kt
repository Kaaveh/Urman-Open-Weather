package ir.kaaveh.urmanopenweather.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ir.kaaveh.urmanopenweather.repository.WeatherRepository
import ir.kaaveh.urmanopenweather.repository.db.WeatherDatabase
import ir.kaaveh.urmanopenweather.repository.network.WeatherNetworkDataSource
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val dataSource = WeatherDatabase.getInstance(applicationContext)

        return try {
            val repository = WeatherRepository(WeatherNetworkDataSource(), dataSource)
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

}