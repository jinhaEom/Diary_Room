package bu.ac.kr.diaryroom.setting

import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentSettingBinding

class SettingFragment(override val layoutResourceId : Int = R.layout.fragment_setting):
    BaseFragment<FragmentSettingBinding>(){
    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = this@SettingFragment

        viewDataBinding.apply{


        }
    }

    override fun observeData() {
    }
}