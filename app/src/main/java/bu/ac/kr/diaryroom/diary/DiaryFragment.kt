package bu.ac.kr.diaryroom.diary

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentDiaryBinding
import bu.ac.kr.diaryroom.home.HomeFragmentDirections
import bu.ac.kr.diaryroom.utils.getNavOptions

class DiaryFragment(override val layoutResourceId : Int = R.layout.fragment_diary):
    BaseFragment<FragmentDiaryBinding>(){

    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = this@DiaryFragment


        viewDataBinding.apply{
            homeToolbarRightButton.setOnClickListener {
                    findNavController().navigate(DiaryFragmentDirections.actionDiaryWrittingFragment(), getNavOptions)
            }
        }
    }

    override fun observeData() {
    }
}