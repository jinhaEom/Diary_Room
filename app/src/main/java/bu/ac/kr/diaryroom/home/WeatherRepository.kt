package bu.ac.kr.diaryroom.home

import android.util.Log
import bu.ac.kr.diaryroom.Interface.ApiObject
import bu.ac.kr.diaryroom.data.WEATHER
import retrofit2.Call

class WeatherRepository {
    fun getWeather(base_date: String, base_time: String, nx: String, ny: String): Call<WEATHER> {
        Log.d("WeatherRepository", "Getting weather data for date $base_date time $base_time nx $nx ny $ny")
        return ApiObject.retrofitService.GetWeather(60, 1, "JSON", base_date, base_time, nx, ny)
    }
}

