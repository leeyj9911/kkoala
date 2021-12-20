package com.example.aws.src.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aws.R
import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentFifthSlideBinding

class FifthSlideFragment : BaseFragment<FragmentFifthSlideBinding> (FragmentFifthSlideBinding::bind, R.layout.fragment_fifth_slide) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.test5.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/tempt_korea/?hl=ko"))
            startActivity(intent)
        }
    }
}