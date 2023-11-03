package bu.ac.kr.diaryroom.diary

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentDiaryBinding
import bu.ac.kr.diaryroom.diary.adapter.DiaryAdapter
import bu.ac.kr.diaryroom.diary.data.DiaryItem
import bu.ac.kr.diaryroom.home.HomeFragmentDirections
import bu.ac.kr.diaryroom.utils.getNavOptions

class DiaryFragment(override val layoutResourceId : Int = R.layout.fragment_diary):
    BaseFragment<FragmentDiaryBinding>(){

    private val diaryAdapter = DiaryAdapter(ArrayList())

    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = this@DiaryFragment
        viewDataBinding.diaryRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewDataBinding.diaryRecyclerView.adapter = diaryAdapter // RecyclerView에 어댑터 설정
        val newItem = DiaryItem("2023년 10월 2일", "즐거웠던 오늘의 일기") // 예시 item 추가 해보기
        diaryAdapter.addItem(newItem)


        viewDataBinding.apply{
            homeToolbarRightButton.setOnClickListener {
                    findNavController().navigate(DiaryFragmentDirections.actionDiaryWrittingFragment(), getNavOptions)
            }
        }
    }

    override fun observeData() {
    }
}