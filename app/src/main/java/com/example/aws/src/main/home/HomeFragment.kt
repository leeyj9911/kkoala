package com.example.aws.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.aws.*
import com.example.aws.category.ITEM.PostItem
import com.example.aws.category.second.SecondAdapter
import com.example.aws.category.second.SecondItemService

import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentHomeBinding
import com.example.aws.src.main.home.model2.PersonalResponse

data class HomePersonalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String)
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),HomePersonalFragmentView {
    val HomePersonalArrayList = ArrayList<HomePersonalItemData>()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ALL_ITEM=1
        HomePersonalService(this).tryGetItem(ALL_ITEM)

        val adapter = childFragmentManager.let { SlideAdapter(it) }
        adapter.addFragment(SlideFragment())
        adapter.addFragment(SlideSecondFragment())
        adapter.addFragment(ThirdSlideFragment())
        adapter.addFragment(FourthFragment())
        adapter.addFragment(FifthSlideFragment())
        adapter.addFragment(SixSlideFragment())
        binding.adViewpager.adapter =adapter
        binding.adViewpager.addOnPageChangeListener(object  : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }


            override fun onPageSelected(position: Int) {
                binding.firstCurcle.setImageResource(R.drawable.viewshappe2)
                binding.secondCurcle.setImageResource(R.drawable.viewshappe2)
                binding.thirdCurcle.setImageResource(R.drawable.viewshappe2)
                binding.fourCurcle.setImageResource(R.drawable.viewshappe2)
                binding.fifthCurcle.setImageResource(R.drawable.viewshappe2)
                binding.sixCurcle.setImageResource(R.drawable.viewshappe2)


                when(position){
                    0-> binding.firstCurcle.setImageResource(R.drawable.viewshape)
                    1-> binding.secondCurcle.setImageResource(R.drawable.viewshape)
                    2-> binding.thirdCurcle.setImageResource(R.drawable.viewshape)
                    3-> binding.fourCurcle.setImageResource(R.drawable.viewshape)
                    4-> binding.fifthCurcle.setImageResource(R.drawable.viewshape)
                    5-> binding.sixCurcle.setImageResource(R.drawable.viewshape)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })



    }

    override fun onGetItemSuccess(response: PersonalResponse) {
        Log.d("확인",response.Items[0].ITEM_ID.S)
        for (i in 0..227){
            val link = "https://kkoalabucket.s3.ap-northeast-1.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5%E1%84%89%E1%85%AE%E1%84%8C%E1%85%B5%E1%86%B8"
            val itemId = (response.Items[i].ITEM_ID).toString().substring(9 until (response.Items[i].ITEM_ID).toString().length-1)
            val alcoholtype = (response.Items[i].ITEM_TYPE1).toString().substring(12 until (response.Items[i].ITEM_TYPE1).toString().length-1)
            val typechange = when(alcoholtype){
                "소주" -> "%E1%84%89%E1%85%A9%E1%84%8C%E1%85%AE"
                "수입맥주"-> "%E1%84%89%E1%85%AE%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%86%E1%85%A2%E1%86%A8%E1%84%8C%E1%85%AE"
                "전통주"-> "%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%90%E1%85%A9%E1%86%BC%E1%84%8C%E1%85%AE"
                "국산맥주"-> "%E1%84%80%E1%85%AE%E1%86%A8%E1%84%89%E1%85%A1%E1%86%AB%E1%84%86%E1%85%A2%E1%86%A8%E1%84%8C%E1%85%AE"
                "와인"-> "%E1%84%8B%E1%85%AA%E1%84%8B%E1%85%B5%E1%86%AB"
                "양주"-> "%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8C%E1%85%AE"
                else -> "FAIL"
            }
            val imageType = when (alcoholtype){
                "소주" -> "jpg"
                "수입맥주"-> "jpg"
                "전통주"->"JPG"
                "국산맥주"-> "png"
                "와인"-> "JPG"
                "양주"-> "jpg"
                else -> "FAIL"
            }
            val imgLink = "${link}/${typechange}/_${itemId}_.${imageType}"
            HomePersonalArrayList.add(HomePersonalItemData(imgLink,""+response.Items[i].ITEM_NAME.S,""+response.Items[i].ITEM_TYPE4.S+"원",""+response.Items[i].EVENT_VALUE.S,""+response.Items[i].KEYWORD1.S,""+response.Items[i].ITEM_ID.S))
        }
        homeAdapter= HomeAdapter(this,HomePersonalArrayList)
        binding.homeRecycler.adapter = homeAdapter
        homeAdapter.setItemClickListener(object : HomeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val ITEM_ID =  HomePersonalArrayList.get(position).ITEM_ID
                // 키워드
                Log.d("아이템아이디",ITEM_ID)
                val intent = Intent(activity,PersonalActivity::class.java)
                intent.putExtra("ITEM_ID",ITEM_ID)
                startActivity(intent)
                // 아이디 넘기기.
            }
        })// 현재 클릭했을 때 데이터
    }

    override fun onGetItemFailure(message: String) {
        showCustomToast("오류:$message")
    }

}






/*
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.aws.*

import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentHomeBinding
import com.example.aws.src.main.MainActivity
import com.example.aws.src.main.home.HomePagerAdapter
import com.example.aws.src.main.my.MyPageFragment
import android.graphics.Bitmap





class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    private lateinit var activity:Activity
    lateinit var viewpager:ViewPager
    var currentPosition = 0

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

    // 핸들러 설정하기
    val handler = Handler(Looper.getMainLooper()){
        setPage()
        true
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 어댑터 연결하기
        viewpager = binding.adViewpager as ViewPager
        val adapter = HomePagerAdapter(requireContext())
        viewpager.adapter = adapter


        val thread=Thread(PagerRunnable())
        thread.start()

        val tapLayout = binding.tabIndicator
        tapLayout.setupWithViewPager(viewpager)
    }




    // 페이지 변경하기
    fun setPage(){
        if(currentPosition==9) currentPosition=0
        viewpager.setCurrentItem(currentPosition,true)
        currentPosition+=1
    }

    // 3초마다 페이지 넘기기
    inner class PagerRunnable:Runnable{
        override fun run(){
            while (true){
                Thread.sleep(2000)
                handler.sendEmptyMessage(0)
            }
        }
    }


    fun  positionUrl(url: String){
        println(url)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity = MainActivity()
        activity?.startActivity(intent)
        activity.finish()
    }

    fun adUrl(url:String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    @Override
    public override fun onAttach(context:Context) {
        super.onAttach(context);
        if (context is Activity) {
            activity = context as Activity
        }
    }

    public override fun onAttach(activity: Activity) {
        super.onAttach(requireActivity())
        this.activity = activity
    }
}*/
