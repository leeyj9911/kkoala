package com.example.aws.src.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.aws.R
import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentSlideBinding


class SlideFragment : BaseFragment<FragmentSlideBinding>(FragmentSlideBinding::bind,R.layout.fragment_slide) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstSlide.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/kooksoondang/?hl=ko"))
            startActivity(intent)
        }
    }

}