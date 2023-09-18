package bu.ac.kr.diaryroom.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bu.ac.kr.diaryroom.WeatherRepository
import bu.ac.kr.diaryroom.data.WeatherModel

class WeatherViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository() // 날씨 데이터 가져오는 리포지토리 클래스

    // LiveData를 사용하여 날씨 데이터를 저장합니다.
    private val _weatherData = MutableLiveData<List<WeatherModel>>()
    val weatherData: LiveData<List<WeatherModel>> = _weatherData

    // 날씨 데이터를 가져오는 함수
    fun fetchWeatherData(nx: Int, ny: Int) {
        weatherRepository.getWeatherData(nx, ny) { weatherList ->
            _weatherData.postValue(weatherList)
        }
    }
}
