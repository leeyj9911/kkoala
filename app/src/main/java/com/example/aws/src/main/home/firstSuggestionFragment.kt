package com.example.aws.src.main.home

import android.os.Bundle
import android.view.View
import com.example.aws.R
import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentFirstSuggestionBinding


class firstSuggestionFragment : BaseFragment<FragmentFirstSuggestionBinding>(FragmentFirstSuggestionBinding::bind,
    R.layout.fragment_first_suggestion) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}