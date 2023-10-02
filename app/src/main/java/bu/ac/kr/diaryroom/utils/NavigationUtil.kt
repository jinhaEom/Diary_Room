package bu.ac.kr.diaryroom.utils

import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import bu.ac.kr.diaryroom.R

val getNavOptions : NavOptions = navOptions {
    anim {
        enter = R.anim.nav_default_enter_anim
        exit = R.anim.nav_default_exit_anim
        popEnter = R.anim.nav_default_pop_enter_anim
        popExit = R.anim.nav_default_pop_exit_anim
    }
}