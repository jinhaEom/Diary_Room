package bu.ac.kr.diaryroom.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bu.ac.kr.diaryroom.Common.Companion.getBaseTime
import bu.ac.kr.diaryroom.WeatherRepository
import bu.ac.kr.diaryroom.data.ITEM
import bu.ac.kr.diaryroom.data.WEATHER
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel : ViewModel() {
    private var base_date = "20210510"  // 발표 일자
    private var base_time = "1400"      // 발표 시각
    private var nx = "55"
    private var ny = "127"
    private val repository = WeatherRepository()

    val weatherData = MutableLiveData<List<ITEM>>()
    val error = MutableLiveData<String>()

    fun setWeather() {

        val cal = Calendar.getInstance()
        base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time) // 현재 날짜
        val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 시각
        val timeM = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 분
        base_time = getBaseTime(timeH, timeM)
        if (timeH == "00" && base_time == "2330") {
            cal.add(Calendar.DATE, -1).toString()
            base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)
        }
        repository.getWeather(base_date, base_time, nx, ny)
            .enqueue(object : retrofit2.Callback<WEATHER> {
                override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                    if (response.isSuccessful) {
                        weatherData.value =
                            response.body()?.response?.body?.items?.item ?: emptyList()
                        Log.d("HomeViewModel", "Got weather data ${weatherData.value}")
                    } else {
                        error.value =
                            "Failed to get weather data with response code ${response.code()}"
                        Log.e("HomeViewModel", error.value!!)
                    }
                }

                override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                    error.value = t.message.toString()
                    Log.e("HomeViewModel", "Failed to get weather data wit error ${t.message}")
                }
            })
    }
}



