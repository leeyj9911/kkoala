package com.example.aws.src.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeViewpagerAdpater(manager: FragmentManager):FragmentPagerAdapter(manager) {
    private val mFragmentManager =ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()
    override fun getCount(): Int {
        return mFragmentManager.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentManager.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
    fun addFragment(fragment: Fragment,title:String){
        mFragmentManager.add(fragment)
        mFragmentTitleList.add(title)
        notifyDataSetChanged()
    }
}