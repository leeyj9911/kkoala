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
import com.example.aws.databinding.FragmentFourthBinding

class FourthFragment : BaseFragment<FragmentFourthBinding>(FragmentFourthBinding::bind, R.layout.fragment_fourth) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.test4.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/xrated_kr/"))
            startActivity(intent)
        }
    }
}