package bu.ac.kr.diaryroom.diary.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.diary.data.DiaryItem

class DiaryAdapter(private val items: MutableList<DiaryItem>) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    inner class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.diaryDate)
        val titleTextView: TextView = itemView.findViewById(R.id.diaryTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_diary, parent, false)
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = items[position]
        holder.dateTextView.text = item.date
        holder.titleTextView.text = item.title
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // 아이템을 추가하는 메서드
    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item: DiaryItem) {
        items.add(item)
        notifyDataSetChanged() // 데이터 변경을 RecyclerView에 알림
        Log.d("DiaryAdapter", "아이템 추가됨: $item") // 로그 출력

    }
}


