package bu.ac.kr.diaryroom.home


import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bu.ac.kr.diaryroom.*
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.data.WeatherModel
import bu.ac.kr.diaryroom.databinding.FragmentHomeBinding
import bu.ac.kr.diaryroom.viewModel.WeatherViewModel
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResourceId: Int = R.layout.fragment_home

    private lateinit var viewModel : WeatherViewModel

    @SuppressLint("SetTextI18n")
    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        viewDataBinding.weatherRecyclerView.layoutManager = LinearLayoutManager(context)


        // nx, ny지점의 날씨 가져와서 설정하기
        viewModel.setWeather()



    }

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { weather ->
            if (weather.isNotEmpty()) {
                val weatherArr = arrayOf(WeatherModel(), WeatherModel(), WeatherModel(), WeatherModel(), WeatherModel(), WeatherModel())
                var index = 0

                for (i in weather.indices) {
                    index %= 6

                    when(weather[i].category) {
                        "PTY" -> weatherArr[index].rainType = weather[i].fcstValue     // 강수 형태
                        "REH" -> weatherArr[index].humidity = weather[i].fcstValue     // 습도
                        "SKY" -> weatherArr[index].sky = weather[i].fcstValue          // 하늘 상태
                        "T1H" -> weatherArr[index].temp = weather[i].fcstValue              // 기온
                        else -> continue
                    }
                    index++
                }

                for (i in 0..5)
                    if(i < weather.size)
                        weatherArr[i].fcstTime=weather[i].fcstTime

                viewDataBinding.weatherRecyclerView.adapter= WeatherAdapter(weatherArr)
            }
            Log.d("HomeFragment", "Updating UI with weather data ${weather}")

        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Log.e("HomeFragment", "Updating UI with error message $error")

            if (!error.isNullOrEmpty()) {
                viewDataBinding.tvError.text="api fail: $error\n다시 시도해주세요."
                viewDataBinding.tvError.visibility=View.VISIBLE

            } else{
                viewDataBinding.tvError.visibility=View.GONE
            }
        })
    }
}
