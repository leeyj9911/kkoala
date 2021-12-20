package com.example.aws.src.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.aws.R
import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentThirdSlideBinding

class ThirdSlideFragment : BaseFragment<FragmentThirdSlideBinding>(FragmentThirdSlideBinding::bind,R.layout.fragment_third_slide) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.test3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/official.jinro/?hl=ko"))
            startActivity(intent)
        }
    }
}