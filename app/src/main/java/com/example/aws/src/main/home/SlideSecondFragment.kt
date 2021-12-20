package com.example.aws.src.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.aws.R
import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentSlideSecondBinding


class SlideSecondFragment : BaseFragment<FragmentSlideSecondBinding>(FragmentSlideSecondBinding::bind,R.layout.fragment_slide_second) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.test2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/official.cass/"))
            startActivity(intent)
        }
    }

}