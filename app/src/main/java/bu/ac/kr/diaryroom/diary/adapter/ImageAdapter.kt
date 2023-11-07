package bu.ac.kr.diaryroom.diary.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import bu.ac.kr.diaryroom.R

class ImageAdapter(private val imageFilenames: List<String>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val filename = imageFilenames[position]
        val filePath = holder.itemView.context.filesDir.absolutePath + "/" + filename
        val bitmap = BitmapFactory.decodeFile(filePath) // 이미지 파일 경로에서 비트맵을 로드
        holder.imageView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return imageFilenames.size
    }
}
