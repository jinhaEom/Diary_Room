package bu.ac.kr.diaryroom.diary

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import bu.ac.kr.diaryroom.ImageAdapter
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentDiaryWrittingBinding
import bu.ac.kr.diaryroom.diary.data.DiaryItem
import bu.ac.kr.diaryroom.viewModel.DiaryViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiaryWrittingFragment(override val layoutResourceId: Int = R.layout.fragment_diary_writting) :
    BaseFragment<FragmentDiaryWrittingBinding>() {

    private val diaryViewModel: DiaryViewModel by activityViewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        sharedPreferences = requireContext().getSharedPreferences("DiaryPreferences", Context.MODE_PRIVATE)

        viewDataBinding.diarySaveButton.setOnClickListener {
            // 현재 시간을 문자열로 가져오기
            val currentTime = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분", Locale.getDefault()).format(Date())

            val title = viewDataBinding.diaryTitle.text.toString()
            saveDiary(currentTime, title)

            val newItem = DiaryItem(currentTime, title)

            diaryViewModel.addDiaryItem(newItem)

            findNavController().popBackStack()
        }

    }
    private fun saveDiary(date: String, title: String) {
        val editor = sharedPreferences.edit()
        editor.putString("diary_date", date)
        editor.putString("diary_title", title)
        editor.apply()
    }
    override fun observeData() {
        viewDataBinding.imageRecyclerView.adapter = ImageAdapter(context!!)
    }
}
