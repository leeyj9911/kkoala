package com.example.aws.src.main.my

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.aws.R
import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentWriteReviewBinding
import com.example.aws.src.main.MainActivity
import com.example.aws.src.main.home.HomeFragment

class WriteReviewFragment : BaseFragment<FragmentWriteReviewBinding>(FragmentWriteReviewBinding::bind, R.layout.fragment_write_review){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var score:Double = 0.0

        binding.btnStar1.isSelected = false
        binding.btnStar2.isSelected = false
        binding.btnStar3.isSelected = false
        binding.btnStar4.isSelected = false
        binding.btnStar5.isSelected = false


        binding.btnStar1.setOnClickListener {
            binding.btnStar1.isSelected = true
            binding.btnStar2.isSelected = false
            binding.btnStar3.isSelected = false
            binding.btnStar4.isSelected = false
            binding.btnStar5.isSelected = false
            score = 1.0
        }

        binding.btnStar2.setOnClickListener {
            binding.btnStar1.isSelected = true
            binding.btnStar2.isSelected = true
            binding.btnStar3.isSelected = false
            binding.btnStar4.isSelected = false
            binding.btnStar5.isSelected = false
            score = 2.0
        }

        binding.btnStar3.setOnClickListener {
            binding.btnStar1.isSelected = true
            binding.btnStar2.isSelected = true
            binding.btnStar3.isSelected = true
            binding.btnStar4.isSelected = false
            binding.btnStar5.isSelected = false
            score = 3.0
        }

        binding.btnStar4.setOnClickListener {
            binding.btnStar1.isSelected = true
            binding.btnStar2.isSelected = true
            binding.btnStar3.isSelected = true
            binding.btnStar4.isSelected = true
            binding.btnStar5.isSelected = false
            score = 4.0
        }

        binding.btnStar5.setOnClickListener {
            binding.btnStar1.isSelected = true
            binding.btnStar2.isSelected = true
            binding.btnStar3.isSelected = true
            binding.btnStar4.isSelected = true
            binding.btnStar5.isSelected = true
            score = 5.0
        }


        binding.btnWriteReviewSave.setOnClickListener {
            if (binding.writeReviewEditText.text.toString().length==0 || binding.btnStar1.isSelected == false){
                Toast.makeText(requireContext(), "리뷰 작성이 되지 않았어요.", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(requireContext(), "소중한 리뷰 감사합니다.", Toast.LENGTH_LONG).show()
                (activity as MainActivity).changeFragment(HomeFragment())
            }
        }



    }
}