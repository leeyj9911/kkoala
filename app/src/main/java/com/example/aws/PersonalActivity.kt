package com.example.aws

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivityPersonalBinding
import com.example.aws.personal.PersonalResultResponse
import com.example.aws.src.main.home.model2.PersonalResponse

class PersonalActivity : BaseActivity<ActivityPersonalBinding>(ActivityPersonalBinding::inflate),PersonalActivityView,CheckPersonalActivityView {
    var ITEM_ID1=0
    var ITEM_ID2=0
    var ITEM_ID3=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val input_itemId = intent.getStringExtra("ITEM_ID")// itemid 받아오기 성공
        Log.d("item",input_itemId.toString())
        PersonalService(this).tryGetItem(input_itemId)
    }

    override fun onGetItemSuccess(response: PersonalResultResponse) {
        Log.d("결과 아이디",response.Items[0].output_recommendedItems_001.S)
        ITEM_ID1 = response.Items[0].output_recommendedItems_001.S.toInt()
        ITEM_ID2 = response.Items[0].output_recommendedItems_002.S.toInt()
        ITEM_ID3 = response.Items[0].output_recommendedItems_003.S.toInt()


        Log.d("item_id",ITEM_ID1.toString())
        Log.d("item_id",ITEM_ID2.toString())
        Log.d("item_id",ITEM_ID3.toString())

        val ALL_ITEM=1
        CheckPersonalService(this).tryGetItem(ALL_ITEM)
    }

    override fun onGetItemSuccess(response: PersonalResponse) {
        Log.d("확인",response.Items[0].ITEM_NAME.S)
        Log.d("second",ITEM_ID1.toString())
        for(i in 0..227){

            if (ITEM_ID1 == response.Items[i].ITEM_ID.S.toInt()){
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
                Glide.with(this).load(imgLink).into(binding.itemIm)
                binding.itemKeyword.text = response.Items[i].KEYWORD1.S
                binding.itemName.text = response.Items[i].ITEM_NAME.S
                binding.itemPrice.text =response.Items[i].ITEM_TYPE4.S
                binding.itemSt.text =response.Items[i].EVENT_VALUE.S

            }
            else if (ITEM_ID2 == response.Items[i].ITEM_ID.S.toInt()){
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
                Glide.with(this).load(imgLink).into(binding.itemIm2)
                binding.itemKeyword2.text = response.Items[i].KEYWORD1.S
                binding.itemName2.text = response.Items[i].ITEM_NAME.S
                binding.itemPrice2.text =response.Items[i].ITEM_TYPE4.S
                binding.itemSt2.text =response.Items[i].EVENT_VALUE.S
            }
            else if (ITEM_ID3 == response.Items[i].ITEM_ID.S.toInt()){
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
                Glide.with(this).load(imgLink).into(binding.itemIm3)
                binding.itemKeyword3.text = response.Items[i].KEYWORD1.S
                binding.itemName3.text = response.Items[i].ITEM_NAME.S
                binding.itemPrice3.text =response.Items[i].ITEM_TYPE4.S
                binding.itemSt3.text =response.Items[i].EVENT_VALUE.S
            }
        }
    }

    override fun onGetItemFailurse(message: String) {
        showCustomToast("오류:$message")
    }

    override fun onGetItemFailure(message: String) {
        showCustomToast("오류:$message")
    }
}