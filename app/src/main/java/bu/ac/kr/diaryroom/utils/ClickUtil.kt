package bu.ac.kr.diaryroom.utils

import android.view.View

class OneClickListener (private val interval: Long = 500L, var onOneClick: (View) -> Unit) : View.OnClickListener {

    @Synchronized
    override fun onClick (v: View?) {
        v?.run{
            isEnabled = false
            postDelayed({ isEnabled = true }, interval)
            onOneClick(this)
        }
    }
}
fun View.setOnOneClickListener(onClick: (View) -> Unit){
    val oneClick = OneClickListener{
        onClick(it)
    }
    setOnClickListener(oneClick)
}