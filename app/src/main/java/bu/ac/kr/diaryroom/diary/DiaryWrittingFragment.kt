package bu.ac.kr.diaryroom.diary

import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bu.ac.kr.diaryroom.ImageAdapter
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.WeatherAdapter
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentDiaryBinding
import bu.ac.kr.diaryroom.databinding.FragmentDiaryWrittingBinding

class DiaryWrittingFragment(override val layoutResourceId : Int = R.layout.fragment_diary_writting):
    BaseFragment<FragmentDiaryWrittingBinding>(){
    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewDataBinding.apply{
            val recyclerView = imageRecyclerView
            val layoutManager = LinearLayoutManager(requireContext()) // LinearLayoutManager 또는 다른 레이아웃 매니저 선택
            recyclerView.layoutManager = layoutManager



        }
    }

    override fun observeData() {
        viewDataBinding.imageRecyclerView.adapter= ImageAdapter(context!!)

    }
}