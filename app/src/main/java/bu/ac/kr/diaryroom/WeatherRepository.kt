package bu.ac.kr.diaryroom

import bu.ac.kr.diaryroom.data.ITEM
import bu.ac.kr.diaryroom.data.WEATHER
import bu.ac.kr.diaryroom.data.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class WeatherRepository {
    private val weatherService = ApiObject.getRetrofitService()
    val cal = Calendar.getInstance()
    val baseDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time) // 현재 날짜
    val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 시각
    val timeM = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 분
    // API 가져오기 적당하게 변환

    private val baseTime = Common().getBaseTime(timeH, timeM)

    fun getWeatherData(nx: Int, ny: Int, callback: (List<WeatherModel>) -> Unit) {
        val call = weatherService.getWeather(10, 10, "JSON", baseDate, baseTime, nx, ny)

        call.enqueue(object : Callback<WEATHER> {
            override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                if (response.isSuccessful) {
                    val it: List<ITEM> = response.body()?.response?.body?.items?.item ?: emptyList()
                    val weatherList = mutableListOf<WeatherModel>()

                    // 날씨 데이터 파싱 및 ViewModel로 전달
                    for (i in 0 until minOf(it.size, 6)) {
                        val weatherItem = WeatherModel()
                        when (it[i].category) {
                            "PTY" -> weatherItem.rainType = it[i].fcstValue
                            "REH" -> weatherItem.humidity = it[i].fcstValue
                            "SKY" -> weatherItem.sky = it[i].fcstValue
                            "T1H" -> weatherItem.temp = it[i].fcstValue
                        }
                        weatherList.add(weatherItem)
                    }

                    callback(weatherList)
                }
            }

            override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                // 에러 처리
            }
        })
    }
}
