import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.diary.DiaryDetailFragment
import bu.ac.kr.diaryroom.diary.data.DiaryItem
import bu.ac.kr.diaryroom.viewModel.DiaryViewModel
import java.io.File

class DiaryAdapter(private val diaryViewModel: DiaryViewModel) : ListAdapter<DiaryItem, DiaryAdapter.DiaryViewHolder>(DiffCallback()) {

    inner class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.diaryDate)
        val titleTextView: TextView = itemView.findViewById(R.id.diaryTitle)
        val goToDetailBtn: ImageView = itemView.findViewById(R.id.goToDetailBtn)  // 상세화면 이동 버튼
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_diary, parent, false)
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = getItem(position)

        holder.dateTextView.text = item.date
        holder.titleTextView.text = item.title

        // 이미지 파일 확인
        val imageFilenames = item.image.split(",")
        if (imageFilenames.isNotEmpty()) {
            val firstImageFilename = imageFilenames[0]
            val filePath = holder.itemView.context.filesDir.absolutePath + "/" + firstImageFilename
            val file = File(filePath)
            if (!file.exists()) {
                Toast.makeText(holder.itemView.context, "파일을 찾을 수 없습니다: $filePath", Toast.LENGTH_LONG).show()  // 경로를 포함한 메시지 출력
                return
            }
        }


        // 상세 화면으로 이동하는 클릭 리스너 설정
        holder.goToDetailBtn.setOnClickListener {
            diaryViewModel.selectDiaryItem(item)

            Log.d("DiaryAdapter", "Selected item: ${item.title},${item.date},${item.content},${item.image}")  // 아이템의 제목을 로그로 찍음
            it.findNavController().navigate(R.id.actiondiaryDetailFragment)

        }
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

