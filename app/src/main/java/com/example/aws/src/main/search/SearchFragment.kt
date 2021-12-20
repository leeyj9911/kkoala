package com.example.aws.src.main.search

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.example.aws.*
import com.example.aws.category.first.firstActivity
import com.example.aws.category.five.FifthActivity
import com.example.aws.category.four.FourthActivity
import com.example.aws.category.second.SecondActivity
import com.example.aws.category.six.SixActivity
import com.example.aws.category.third.ThirdActivity
import com.example.aws.config.ApplicationClass
import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentSearchBinding


class SearchFragment :
    BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {
    var item: Int = 0
    lateinit var editor: SharedPreferences.Editor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        editor = ApplicationClass.sSharedPreferences.edit()


        //shared 이용해서 카테고리 넘겨주고 카테고리 별 추천을 해줌


        binding.firstCategory.setOnClickListener {//소주


            val intent = Intent(activity, firstActivity::class.java)
            startActivity(intent)
            item = 1
        }
        binding.secondCategory.setOnClickListener {//수입맥주


            val intent = Intent(activity, SecondActivity::class.java)
            startActivity(intent)
            item = 2
        }
        binding.thirdCategory.setOnClickListener {//전통주


            val intent = Intent(activity, ThirdActivity::class.java)
            startActivity(intent)
            item = 3
        }
        binding.fourthCategory.setOnClickListener {//국산맥주


            val intent = Intent(activity, FourthActivity::class.java)
            startActivity(intent)
            item = 4
        }
        binding.fifthCategory.setOnClickListener {//와인


            val intent = Intent(activity, FifthActivity::class.java)
            startActivity(intent)
            item = 5

        }
        binding.sixthCategory.setOnClickListener {//양주


            val intent = Intent(activity, SixActivity::class.java)
            startActivity(intent)
            item = 6
        }

    }


}



