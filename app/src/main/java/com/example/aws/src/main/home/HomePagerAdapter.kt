package com.example.aws.src.main.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.aws.R
import android.widget.Toast




class HomePagerAdapter(private val context: Context): PagerAdapter() {

    val Image= arrayOf(
        R.drawable.chocosoju,
        R.drawable.xrated,
        R.drawable.jinro,
        R.drawable.babambar,
        R.drawable.cass,
        R.drawable.tempt,
        R.drawable.filgood,
        R.drawable.filite,
        R.drawable.heineken
    )

    val imageUrl = arrayOf(
        "https://www.instagram.com/firstsoju/",
        "https://www.instagram.com/xrated_kr/",
        "https://www.instagram.com/official.jinro/?hl=ko",
        "https://www.instagram.com/kooksoondang/?hl=ko",
        "https://instagram.com/official.cass/",
        "https://www.instagram.com/tempt_korea/?hl=ko",
        "https://www.instagram.com/filgood_official/",
        "https://www.instagram.com/official.filite/?hl=ko",
        "https://www.instagram.com/heineken_kr/?hl=ko"
    )

    override fun getCount(): Int {
        return Image.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val v = inflater.inflate(R.layout.viewpager_fragment, container, false)
        val image = v.findViewById<View>(R.id.viewpager_imageview) as ImageView


        image.setOnClickListener{
            //HomeFragment().positionNum(position)
            //HomeFragment().positionUrl(imageUrl[position])
        }

        image.setImageResource(Image[position])
        val vp = container as ViewPager
        vp.addView(v, 0)

        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }

}