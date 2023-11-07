package bu.ac.kr.diaryroom.diary.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.diary.DiaryWrittingFragment
import com.bumptech.glide.Glide

class MultiImageAdapter(private val items: ArrayList<Uri>, val context: DiaryWrittingFragment) :
    RecyclerView.Adapter<MultiImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MultiImageAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MultiImageAdapter.ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context).load(item)
            .override(500,500)
            .into(holder.image)
    }

    override fun getItemCount(): Int = items.size
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        var image = v.findViewById<ImageView>(R.id.image)

        fun bind(listener: View.OnClickListener, item: String) {
            view.setOnClickListener(listener)
        }
    }

}
