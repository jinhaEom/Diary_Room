package bu.ac.kr.diaryroom.diary

import DiaryAdapter
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentDiaryBinding
import bu.ac.kr.diaryroom.diary.data.DiaryItem
import bu.ac.kr.diaryroom.utils.getNavOptions
import bu.ac.kr.diaryroom.viewModel.DiaryViewModel

class DiaryFragment(override val layoutResourceId: Int = R.layout.fragment_diary) :
    BaseFragment<FragmentDiaryBinding>() {
    private lateinit var sharedPreferences: SharedPreferences

    private val diaryViewModel: DiaryViewModel by activityViewModels()

    private val diaryAdapter = DiaryAdapter() // DiaryAdapter 초기화

    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = this@DiaryFragment
        viewDataBinding.diaryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        sharedPreferences =
            requireContext().getSharedPreferences("DiaryPreferences", Context.MODE_PRIVATE)

        viewDataBinding.diaryRecyclerView.adapter = diaryAdapter
        // ViewModel에서 LiveData를 관찰하여 데이터 업데이트 감지
        diaryViewModel.getDiaryItems().observe(viewLifecycleOwner) { items ->
            diaryAdapter.submitList(items)
        }
        viewDataBinding.apply {
            homeToolbarRightButton.setOnClickListener {
                findNavController().navigate(
                    DiaryFragmentDirections.actionDiaryWrittingFragment(),
                    getNavOptions
                )
            }
            val date = sharedPreferences.getString("diary_date", "")
            val title = sharedPreferences.getString("diary_title", "")

            if (!date.isNullOrEmpty() && !title.isNullOrEmpty()) {
                val newItem = DiaryItem(date, title)
                diaryAdapter.addItem(newItem)
            }
        }
    }

    override fun observeData() {
    }
}
