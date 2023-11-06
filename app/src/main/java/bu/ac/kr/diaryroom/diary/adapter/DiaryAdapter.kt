import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.diary.data.DiaryItem

class DiaryAdapter : ListAdapter<DiaryItem, DiaryAdapter.DiaryViewHolder>(DiffCallback()) {

    inner class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.diaryDate)
        val titleTextView: TextView = itemView.findViewById(R.id.diaryTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_diary, parent, false)
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = getItem(position)
        holder.dateTextView.text = item.date
        holder.titleTextView.text = item.title
    }

    class DiffCallback : DiffUtil.ItemCallback<DiaryItem>() {
        override fun areItemsTheSame(oldItem: DiaryItem, newItem: DiaryItem): Boolean {
            return oldItem.date == newItem.date && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: DiaryItem, newItem: DiaryItem): Boolean {
            return oldItem == newItem
        }
    }

    // 아이템을 추가하는 메서드
    fun addItem(item: DiaryItem) {
        submitList(currentList + listOf(item))
    }
}
