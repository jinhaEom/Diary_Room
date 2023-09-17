package bu.ac.kr.diaryroom.diary

import androidx.navigation.fragment.findNavController
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentDiaryBinding

class DiaryFragment(override val layoutResourceId : Int = R.layout.fragment_diary):
    BaseFragment<FragmentDiaryBinding>(){
    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = this@DiaryFragment

        viewDataBinding.apply{

        }
    }

    override fun observeData() {
    }
}