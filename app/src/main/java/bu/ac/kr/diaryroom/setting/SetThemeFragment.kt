package bu.ac.kr.diaryroom.setting

import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentSetThemeBinding

class SetThemeFragment(override val layoutResourceId: Int = R.layout.fragment_set_theme) :
    BaseFragment<FragmentSetThemeBinding>() {
    override fun aboutBinding() {
        viewDataBinding.themeRadioGroup.setOnCheckedChangeListener{_, checkedId ->
            when(checkedId){
                R.id.darkModeBtn -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                R.id.lightModeBtn ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                }
                R.id.systemBtn ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
        viewDataBinding.backArrowBtn.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    override fun observeData() {
    }
}