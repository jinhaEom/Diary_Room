package bu.ac.kr.diaryroom.home

import androidx.navigation.fragment.findNavController
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentHomeBinding

class HomeFragment(override val layoutResourceId : Int = R.layout.fragment_home):
    BaseFragment<FragmentHomeBinding>(){
    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = this@HomeFragment

        viewDataBinding.apply{

        }
    }

    override fun observeData() {
    }
}