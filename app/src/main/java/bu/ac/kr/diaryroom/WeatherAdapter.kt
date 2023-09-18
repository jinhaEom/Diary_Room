package bu.ac.kr.diaryroom

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bu.ac.kr.diaryroom.data.WeatherModel


class WeatherAdapter (var items : Array<WeatherModel>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    // 뷰 홀더 만들어서 반환, 뷰릐 레이아웃은 list_item_weather.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_weather, parent, false)
        return ViewHolder(itemView)
    }

    // 전달받은 위치의 아이템 연결
    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    // 아이템 갯수 리턴
    override fun getItemCount() = items.count()

    // 뷰 홀더 설정
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun setItem(item: WeatherModel) {
            val imgWeather = itemView.findViewById<ImageView>(R.id.imgWeather)
            val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
            val tvHumidity = itemView.findViewById<TextView>(R.id.tvHumidity)
            val tvTemp = itemView.findViewById<TextView>(R.id.tvTemp)

            imgWeather.setImageResource(getRainImage(item.rainType, item.sky))
            tvTime.text = getTime(item.fcstTime)
            tvHumidity.text = item.humidity + "%"
            tvTemp.text = item.temp + "°"
        }
    }

    fun getTime(factTime: String): String {
        try {
            val hourSystem = factTime.toInt()

            if (hourSystem == 0) {
                return "오전 12시"
            } else if (hourSystem >= 2100) {
                val hourSystemString = (hourSystem - 1200).toString()
                return "오후 ${hourSystemString[0]}${hourSystemString[1]}시"
            } else if (hourSystem == 1200) {
                return "오후 12시"
            } else if (hourSystem > 1200) {
                val hourSystemString = (hourSystem - 1200).toString()
                return "오후 ${hourSystemString[0]}시"
            } else if (hourSystem >= 1000) {
                val hourSystemString = hourSystem.toString()
                return "오전 ${hourSystemString[0]}${hourSystemString[1]}시"
            } else {
                val hourSystemString = hourSystem.toString()
                return "오전 ${hourSystemString[0]}시"
            }
        } catch (e: NumberFormatException) {
            return "올바른 시간 형식이 아닙니다."
        }
    }


    // 강수 형태
    fun getRainImage(rainType : String, sky: String) : Int {
        return when(rainType) {
            "0" -> getWeatherImage(sky)
            "1" -> R.drawable.rainy
            "2" -> R.drawable.hail
            "3" -> R.drawable.snowy
            "4" -> R.drawable.brash
            else -> getWeatherImage(sky)
        }
    }

    fun getWeatherImage(sky : String) : Int {
        // 하늘 상태
        return when(sky) {
            "1" -> R.drawable.sun                       // 맑음
            "3" ->  R.drawable.cloudy                     // 구름 많음
            "4" -> R.drawable.blur                 // 흐림
            else -> R.drawable.ic_launcher_foreground   // 오류
        }
    }


}