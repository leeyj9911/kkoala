package com.example.aws

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivityDetailBinding
import com.example.aws.src.main.MainActivity
import com.example.aws.src.main.home.model.ItemResponse

class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate),DetailActivityView {
    var keyword1:String = ""
    var keyword2:String = ""
    var keyword3:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ITEM_ID = intent.getStringExtra("ITEM_ID")// itemid 받아오기 성공
        keyword1 = "#${intent.getStringExtra("keyword1").toString()}"
        keyword2 = "#${intent.getStringExtra("keyword2").toString()}"
        keyword3 = "#${intent.getStringExtra("keyword3").toString()}"

        if(keyword1=="#1") keyword1 = " "
        if(keyword2=="#1") keyword2 = " "
        if(keyword3=="#1") keyword3 = " "

        Log.d("확인",ITEM_ID.toString())
        DetailService(this).tryGetItem(ITEM_ID)
        //binding.test.text = item
    }

    override fun onGetItemSuccess(response: ItemResponse) {

        val link = "https://kkoalabucket.s3.ap-northeast-1.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5%E1%84%89%E1%85%AE%E1%84%8C%E1%85%B5%E1%86%B8"
        val itemId = (response.Items[0].ITEM_ID).toString().substring(9 until (response.Items[0].ITEM_ID).toString().length-1)
        val alcoholtype = (response.Items[0].ITEM_TYPE1).toString().substring(12 until (response.Items[0].ITEM_TYPE1).toString().length-1)
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
        val alcoholname = (response.Items[0].ITEM_NAME).toString().substring(11 until (response.Items[0].ITEM_NAME).toString().length-1)
        val alcoholity = (response.Items[0].ITEM_VALUE1).toString().substring(13 until (response.Items[0].ITEM_VALUE1).toString().length-1)
        val alcoholprice = (response.Items[0].ITEM_TYPE4).toString().substring(12, (response.Items[0].ITEM_TYPE4).toString().length-1)
        val alcoholscore = (response.Items[0].EVENT_VALUE).toString().substring(13 until (response.Items[0].EVENT_VALUE).toString().length-1)
        val alcoholRV1 = (response.Items[0].EVENT_VALUE1).toString().substring(14 until (response.Items[0].EVENT_VALUE1).toString().length-1)
        val alcoholRV2 = (response.Items[0].EVENT_VALUE2).toString().substring(14 until (response.Items[0].EVENT_VALUE2).toString().length-1)
        val alcoholRV3 = (response.Items[0].EVENT_VALUE3).toString().substring(14 until  (response.Items[0].EVENT_VALUE3).toString().length-1)


        //여기서 상세 페이지에서 사용할거 넣으면 됨 ex) response.Item[0]. 이 뒤에서 필요한거 사용해서 쓰면됩니당.
        Glide.with(this).load(imgLink).into(binding.detailAlcoholimg)
        binding.detailAlcoholname.text = "${alcoholname}"
        binding.detailAlcoholity.text = "도수 : ${alcoholity} %"
        binding.detailPrice.text = "가격 : ${alcoholprice} "
        binding.detailScore.text = "${alcoholscore}"
        binding.detailKeyword.text = "${keyword1} ${keyword2} ${keyword3}"
        binding.tvReview1.text = "\"${alcoholRV1}\""
        binding.tvReview2.text = "\"${alcoholRV2}\""
        binding.tvReview3.text = "\"${alcoholRV3}\""

        binding.btnWiki.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://namu.wiki/w/"+"${alcoholname}"))
            startActivity(intent)
        }
        binding.btnYoutube.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query="+"${alcoholname}"))
            startActivity(intent)
        }

        binding.btnLike.setOnClickListener {
            Toast.makeText(this, "소중한 의견 감사드립니다:)", Toast.LENGTH_LONG).show()
            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnDislike.setOnClickListener {
            Toast.makeText(this, "소중한 의견 감사드립니다:)", Toast.LENGTH_LONG).show()
            val intent = Intent(this@DetailActivity,MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onGetItemFailure(message: String) {
        showCustomToast("오류:$message")
    }
}