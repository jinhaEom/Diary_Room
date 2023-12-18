package bu.ac.kr.diaryroom.setting

import androidx.navigation.fragment.findNavController
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentSettingBinding
import bu.ac.kr.diaryroom.utils.getNavOptions
import bu.ac.kr.diaryroom.utils.setOnOneClickListener

class SettingFragment(override val layoutResourceId : Int = R.layout.fragment_setting):
    BaseFragment<FragmentSettingBinding>(){
    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewDataBinding.apply{
            setThemeTx.setOnOneClickListener {
                findNavController().navigate(SettingFragmentDirections.actionsetThemeFragment(),
                    getNavOptions)
            }
            arrowBack.setOnClickListener{
                findNavController().navigateUp()
            }

        }
    }

    override fun observeData() {
    }
}