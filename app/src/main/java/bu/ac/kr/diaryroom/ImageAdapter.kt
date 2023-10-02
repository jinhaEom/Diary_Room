package bu.ac.kr.diaryroom

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bu.ac.kr.diaryroom.data.ImageItem
import com.bumptech.glide.Glide

class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val images = mutableListOf<ImageItem>()
    private val MAX_IMAGES = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageItem = images[position]
        Glide.with(context)
            .load(imageItem.imageUri)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun addImage(imageUri: Uri) {
        if (images.size < MAX_IMAGES) {
            images.add(ImageItem(imageUri))
            notifyDataSetChanged()
        } else {
            // 이미지 선택이 최대 개수에 도달했을 때 처리할 로직 추가
            // 여기에서는 예시로 토스트 메시지를 표시하도록 합니다.
            Toast.makeText(context, "최대 이미지 개수에 도달했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}
