package bu.ac.kr.diaryroom.home

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import bu.ac.kr.diaryroom.*
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.data.ITEM
import bu.ac.kr.diaryroom.data.WEATHER
import bu.ac.kr.diaryroom.data.WeatherModel
import bu.ac.kr.diaryroom.databinding.FragmentHomeBinding
import bu.ac.kr.diaryroom.viewModel.WeatherViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Response


import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResourceId: Int = R.layout.fragment_home
    private var curPoint : Point? = null    // 현재 위치의 격자 좌표를 저장할 포인트
    private lateinit var viewModel: WeatherViewModel


    @SuppressLint("SetTextI18n")
    override fun aboutBinding() {
        val permissionList = arrayOf<String>(
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION,
        )
        ActivityCompat.requestPermissions(requireActivity(), permissionList, 1)
        // 오늘 날짜 텍스트뷰 설정
        viewDataBinding.tvDate.text = SimpleDateFormat("MM월 dd일", Locale.getDefault()).format(Calendar.getInstance().time) + "날씨"
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        // 날씨 데이터를 관찰하고 업데이트를 처리하는 코드
        viewModel.weatherData.observe(viewLifecycleOwner) { weatherList ->
            // 날씨 데이터 업데이트 처리
            updateWeatherUI(weatherList)
        }
        requestLocation()

        // <새로고침> 버튼 누를 때 위치 정보 & 날씨 정보 다시 가져오기
        viewDataBinding.btnRefresh.setOnClickListener {
            requestLocation()
        }



    }
    @SuppressLint("MissingPermission")
    fun requestLocation() {
        val locationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        try {
            // 나의 현재 위치 요청
            val locationRequest = LocationRequest.create()
            locationRequest.run {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = 60 * 1000    // 요청 간격(1초)
            }

            val locationCallback = object : LocationCallback() {
                // 요청 결과
                @SuppressLint("SetTextI18n")
                override fun onLocationResult(p0: LocationResult) {
                    p0.let {
                        for (location in it.locations) {


                            // 현재 위치의 위경도를 격자 좌표로 변환
                            curPoint = Common().dfsXyConv(location.latitude, location.longitude)

                            // 오늘 날짜 텍스트뷰 설정
                            viewDataBinding.tvDate.text = SimpleDateFormat("MM월 dd일", Locale.getDefault()).format(Calendar.getInstance().time) + " 날씨"
                            // nx, ny지점의 날씨 가져와서 설정하기
                            viewModel.fetchWeatherData(curPoint!!.x,curPoint!!.y)

                                                    }
                    }
                }
            }

            // 내 위치 실시간으로 감지
            Looper.myLooper()?.let {
                locationClient.requestLocationUpdates(locationRequest, locationCallback,
                    it)
            }


        } catch (e : SecurityException) {
            e.printStackTrace()
        }
    }
    override fun observeData() {

    }
    private fun updateWeatherUI(weatherList: List<WeatherModel>) {
        if (weatherList.isNotEmpty()) {
            val weatherArr = weatherList.toTypedArray()
            weatherArr[0].fcstTime = "지금"
            viewDataBinding.weatherRecyclerView.adapter = WeatherAdapter(weatherArr)
        } else {
            Toast.makeText(requireContext(), "날씨 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

}

