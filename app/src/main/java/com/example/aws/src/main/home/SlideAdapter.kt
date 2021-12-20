package com.example.aws.src.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SlideAdapter(manager: FragmentManager):FragmentPagerAdapter(manager) {
    private val mFragmentList = ArrayList<Fragment>()
    override fun getCount(): Int{
        return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }
    fun addFragment(fragment: Fragment){
        mFragmentList.add(fragment)
        notifyDataSetChanged()
    }
}