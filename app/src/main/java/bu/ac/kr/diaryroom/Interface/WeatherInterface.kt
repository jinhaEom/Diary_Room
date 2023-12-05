package bu.ac.kr.diaryroom.Interface


import bu.ac.kr.diaryroom.BuildConfig
import bu.ac.kr.diaryroom.data.WEATHER
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {
    // getUltraSrtFcst : 초단기 예보 조회 + 인증키
    @GET("getUltraSrtFcst")
    fun GetWeather(@Query("serviceKey") serviceKey: String = BuildConfig.WEATHER_API_KEY,
                   @Query("numOfRows") num_of_rows : Int,   // 한 페이지 경과 수
                   @Query("pageNo") page_no : Int,          // 페이지 번호
                   @Query("dataType") data_type : String,   // 응답 자료 형식
                   @Query("base_date") base_date : String,  // 발표 일자
                   @Query("base_time") base_time : String,  // 발표 시각
                   @Query("nx") nx : String,                // 예보지점 X 좌표
                   @Query("ny") ny : String)                // 예보지점 Y 좌표
            : Call<WEATHER>
}

// xml 파일 형식을 data class로 구현
data class WEATHER (val response : RESPONSE)
data class RESPONSE(val header : HEADER, val body : BODY)
data class HEADER(val resultCode : Int, val resultMsg : String)
data class BODY(val dataType : String, val items : ITEMS, val totalCount : Int)
data class ITEMS(val item : List<ITEM>)
// category : 자료 구분 코드, fcstDate : 예측 날짜, fcstTime : 예측 시간, fcstValue : 예보 값
data class ITEM(val category : String, val fcstDate : String, val fcstTime : String, val fcstValue : String)

// retrofit을 사용하기 위한 빌더 생성
val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/")
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


val gson : Gson = GsonBuilder()
    .setLenient()
    .create()
object ApiObject {
    val retrofitService: WeatherInterface by lazy {
        retrofit.create(WeatherInterface::class.java)
    }
}