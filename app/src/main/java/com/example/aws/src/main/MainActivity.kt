package com.example.aws.src.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.aws.R
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivityMainBinding
import com.example.aws.src.main.home.HomeFragment
import com.example.aws.src.main.my.MyPageFragment
import com.example.aws.src.main.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainBtnNav.itemIconTintList = null // 바텀 네비게이션뷰 잘되게 하는 코드
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        binding.mainBtnNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when(item.itemId){
                    R.id.main_nevi_home ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm,HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.main_nevi_serch ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, SearchFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.main_nevi_my ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
        )
    }

    fun changeFragment(f : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, f).commit()
    }

    val imageUrl = arrayOf(
        "https://www.instagram.com/firstsoju/",
        "https://www.instagram.com/xrated_kr/",
        "https://www.instagram.com/official.jinro/?hl=ko",
        "https://www.instagram.com/kooksoondang/?hl=ko",
        "https://instagram.com/official.cass/",
        "https://www.instagram.com/tempt_korea/?hl=ko",
        "https://www.instagram.com/filgood_official/",
        "https://www.instagram.com/official.filite/?hl=ko",
        "https://www.instagram.com/heineken_kr/?hl=ko"
    )

    fun addUri(url:String){
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    fun toast(msg:String){
        Toast.makeText(this, "${msg}", Toast.LENGTH_SHORT).show()
    }
}
