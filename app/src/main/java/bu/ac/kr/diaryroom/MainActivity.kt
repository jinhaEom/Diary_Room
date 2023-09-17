package bu.ac.kr.diaryroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import bu.ac.kr.diaryroom.diary.DiaryFragment
import bu.ac.kr.diaryroom.home.HomeFragment
import bu.ac.kr.diaryroom.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val frame: RelativeLayout by lazy { // activity_main의 화면 부분
        findViewById(R.id.body_container)
    }
    private val bottomNagivationView: BottomNavigationView by lazy { // 하단 네비게이션 바
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 애플리케이션 실행 후 첫 화면 설정
        if (savedInstanceState == null) { // 앱이 처음 실행될 때만 홈 프래그먼트를 추가합니다.
            supportFragmentManager.beginTransaction().add(frame.id, HomeFragment()).commit()
            bottomNagivationView.selectedItemId = R.id.homeFragment
        }

        // 하단 네비게이션 바 클릭 이벤트 설정
        bottomNagivationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.diaryFragment -> {
                    replaceFragment(DiaryFragment())
                    true
                }
                R.id.homeFragment -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.settingFragment -> {
                    replaceFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }
    }


    // 화면 전환 구현 메소드
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }
}
