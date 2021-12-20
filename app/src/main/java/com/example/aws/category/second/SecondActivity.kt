package com.example.aws.category.second

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.aws.DetailActivity
import com.example.aws.R
import com.example.aws.category.ITEM.ItemPostResponse
import com.example.aws.category.ITEM.PostItem
import com.example.aws.category.first.FirstAdapter
import com.example.aws.category.first.ItemService

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivitySecondBinding

data class SecondItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String)
data class FirstBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String,val keyword2:String)
data class SecondBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String,val keyword2:String)
data class ThirdBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String,val keyword2:String)
data class FourthBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String,val keyword2:String)
data class FifthBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String,val keyword2:String)
data class SixBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String,val keyword2:String)
data class SevenBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String,val keyword2:String)
data class EightBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String,val keyword2:String)

class SecondActivity : BaseActivity<ActivitySecondBinding>(ActivitySecondBinding::inflate),SecondCategoryActivityView,SecondItemActivityView {

    val SecondItemArrayList = ArrayList<SecondItemData>()
    val FirstBeerItemArrayList = ArrayList<FirstBeerItemData>()
    val SecondBeerArrayList = ArrayList<SecondBeerItemData>()
    val ThirdBeerItemArrayList = ArrayList<ThirdBeerItemData>()
    val FourthBeerItemArrayList = ArrayList<FourthBeerItemData>()
    val FifthBeerItemArrayList = ArrayList<FifthBeerItemData>()
    val SixBeerItemArrayList = ArrayList<SixBeerItemData>()
    val SevenBeerItemArrayList = ArrayList<SevenBeerItemData>()
    val EightBeerItemArrayList = ArrayList<EightBeerItemData>()
    var category = ""
    var count = 0
    private lateinit var secondAdapter: SecondAdapter
    private lateinit var firstAdapter:FirstBeerAdapter
    private lateinit var secondBeerAdapter:SecondBeerAdapter
    private lateinit var thirdBeerAdapter:ThirdBeerAdapter
    private lateinit var fourthBeerAdapter:FourthBeerAdapter
    private lateinit var fifthBeerAdapter:FifthBeerAdapter
    private lateinit var sixBeerAdapter:SixBeerAdapter
    private lateinit var sevenBeerAdapter:SevenBeerAdapter
    private lateinit var eigthBeerAdapter:EightBeerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


         var  TYPE1_1 = 2
        SecondCategoryService(this).tryGetItem(TYPE1_1)
    }

    override fun onGetItemSuccess(response: ItemCategoryResponse) {
        Log.d("결과",response.Count.toString())

        val link = "https://kkoalabucket.s3.ap-northeast-1.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5%E1%84%89%E1%85%AE%E1%84%8C%E1%85%B5%E1%86%B8/%E1%84%89%E1%85%AE%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%86%E1%85%A2%E1%86%A8%E1%84%8C%E1%85%AE"
        for (i in 0..58){
            val imgID = (response.Items[i].ITEM_ID).toString()
            val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
            val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"
            SecondItemArrayList.add(SecondItemData(alcoholImg,""+response.Items[i].ITEM_NAME.S,""+response.Items[i].ITEM_TYPE4.S+"원",""+response.Items[i].EVENT_VALUE.S,""+response.Items[i].KEYWORD1.S))
        }

        secondAdapter = SecondAdapter(this,SecondItemArrayList)
        binding.firstRecycler.adapter = secondAdapter
        secondAdapter.setItemClickListener(object : SecondAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val ITEM_ID = response.Items[position].ITEM_ID.S
                // 키워드
                val keyword1 = response.Items[position].KEYWORD1.S
                val keyword2 = response.Items[position].KEYWORD2.S
                val keyword3 = response.Items[position].KEYWORD3.S
                Log.d("아이템아이디",ITEM_ID)
                val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                intent.putExtra("ITEM_ID",ITEM_ID)
                intent.putExtra("keyword1",keyword1)
                intent.putExtra("keyword2",keyword2)
                intent.putExtra("keyword3",keyword3)
                startActivity(intent)
                val postItem = PostItem(ITEM_ID = ITEM_ID)
                SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
            }
        })// 현재 클릭했을 때 데이터 받아오기.

        binding.firstCategory.setOnClickListener {
            category = "아시아권"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.VISIBLE
            binding.thirdRecycler.visibility = View.INVISIBLE
            binding.fourthRecycler.visibility = View.INVISIBLE
            binding.fifthRecycler.visibility = View.INVISIBLE
            binding.sixthRecycler.visibility = View.INVISIBLE
            binding.sevenRecycler.visibility = View.INVISIBLE
            binding.eigthRecycler.visibility = View.INVISIBLE
            binding.nineRecycler.visibility = View.INVISIBLE

            binding.firstCategory.isSelected = true
            binding.secondCategory.isSelected = false
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false


            for (i in 0..58){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FirstBeerItemArrayList.add(FirstBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    firstAdapter = FirstBeerAdapter(this,FirstBeerItemArrayList)
                    binding.secondRecycler.adapter = firstAdapter
                    firstAdapter.setItemClickListener(object : FirstBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FirstBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FirstBeerItemArrayList.get(position).keyword
                            val keyword2 = FirstBeerItemArrayList.get(position).keyword1
                            val keyword3 = FirstBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.secondCategory.setOnClickListener {
            category = "유럽권"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.INVISIBLE
            binding.thirdRecycler.visibility = View.VISIBLE
            binding.fourthRecycler.visibility = View.INVISIBLE
            binding.fifthRecycler.visibility = View.INVISIBLE
            binding.sixthRecycler.visibility = View.INVISIBLE
            binding.sevenRecycler.visibility = View.INVISIBLE
            binding.eigthRecycler.visibility = View.INVISIBLE
            binding.nineRecycler.visibility = View.INVISIBLE

            binding.firstCategory.isSelected = false
            binding.secondCategory.isSelected = true
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false


            for (i in 0..58){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SecondBeerArrayList.add(SecondBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    secondBeerAdapter = SecondBeerAdapter(this,SecondBeerArrayList)
                    binding.thirdRecycler.adapter = secondBeerAdapter
                    secondBeerAdapter.setItemClickListener(object : SecondBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SecondBeerArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SecondBeerArrayList.get(position).keyword
                            val keyword2 = SecondBeerArrayList.get(position).keyword1
                            val keyword3 = SecondBeerArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }



        }
        binding.thirdCategory.setOnClickListener {
            category = "북미권"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.INVISIBLE
            binding.thirdRecycler.visibility = View.INVISIBLE
            binding.fourthRecycler.visibility = View.VISIBLE
            binding.fifthRecycler.visibility = View.INVISIBLE
            binding.sixthRecycler.visibility = View.INVISIBLE
            binding.sevenRecycler.visibility = View.INVISIBLE
            binding.eigthRecycler.visibility = View.INVISIBLE
            binding.nineRecycler.visibility = View.INVISIBLE

            binding.firstCategory.isSelected = false
            binding.secondCategory.isSelected = false
            binding.thirdCategory.isSelected = true
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false


            for (i in 0..58){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    ThirdBeerItemArrayList.add(ThirdBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    thirdBeerAdapter = ThirdBeerAdapter(this,ThirdBeerItemArrayList)
                    binding.fourthRecycler.adapter = thirdBeerAdapter
                    thirdBeerAdapter.setItemClickListener(object : ThirdBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = ThirdBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = ThirdBeerItemArrayList.get(position).keyword
                            val keyword2 = ThirdBeerItemArrayList.get(position).keyword1
                            val keyword3 = ThirdBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fourthCategory.setOnClickListener {
            category = "저도수"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.INVISIBLE
            binding.thirdRecycler.visibility = View.INVISIBLE
            binding.fourthRecycler.visibility = View.INVISIBLE
            binding.fifthRecycler.visibility = View.VISIBLE
            binding.sixthRecycler.visibility = View.INVISIBLE
            binding.sevenRecycler.visibility = View.INVISIBLE
            binding.eigthRecycler.visibility = View.INVISIBLE
            binding.nineRecycler.visibility = View.INVISIBLE

            binding.firstCategory.isSelected = false
            binding.secondCategory.isSelected = false
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = true
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false


            for (i in 0..58){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FourthBeerItemArrayList.add(FourthBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fourthBeerAdapter = FourthBeerAdapter(this,FourthBeerItemArrayList)
                    binding.fifthRecycler.adapter = fourthBeerAdapter
                    fourthBeerAdapter.setItemClickListener(object : FourthBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FourthBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FourthBeerItemArrayList.get(position).keyword
                            val keyword2 = FourthBeerItemArrayList.get(position).keyword1
                            val keyword3 = FourthBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fifthCategory.setOnClickListener {
            category = "고도수"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.INVISIBLE
            binding.thirdRecycler.visibility = View.INVISIBLE
            binding.fourthRecycler.visibility = View.INVISIBLE
            binding.fifthRecycler.visibility = View.INVISIBLE
            binding.sixthRecycler.visibility = View.VISIBLE
            binding.sevenRecycler.visibility = View.INVISIBLE
            binding.eigthRecycler.visibility = View.INVISIBLE
            binding.nineRecycler.visibility = View.INVISIBLE

            binding.firstCategory.isSelected = false
            binding.secondCategory.isSelected = false
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = true
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false


            for (i in 0..58){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FifthBeerItemArrayList.add(FifthBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fifthBeerAdapter = FifthBeerAdapter(this,FifthBeerItemArrayList)
                    binding.sixthRecycler.adapter = fifthBeerAdapter
                    fifthBeerAdapter.setItemClickListener(object : FifthBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FifthBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FifthBeerItemArrayList.get(position).keyword
                            val keyword2 = FifthBeerItemArrayList.get(position).keyword1
                            val keyword3 = FifthBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.sixthCategory.setOnClickListener {
            category = "과일류"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.INVISIBLE
            binding.thirdRecycler.visibility = View.INVISIBLE
            binding.fourthRecycler.visibility = View.INVISIBLE
            binding.fifthRecycler.visibility = View.INVISIBLE
            binding.sixthRecycler.visibility = View.INVISIBLE
            binding.sevenRecycler.visibility = View.VISIBLE
            binding.eigthRecycler.visibility = View.INVISIBLE
            binding.nineRecycler.visibility = View.INVISIBLE

            binding.firstCategory.isSelected = false
            binding.secondCategory.isSelected = false
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = true
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false


            for (i in 0..58){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SixBeerItemArrayList.add(SixBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sixBeerAdapter = SixBeerAdapter(this,SixBeerItemArrayList)
                    binding.sevenRecycler.adapter = sixBeerAdapter
                    sixBeerAdapter.setItemClickListener(object : SixBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SixBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SixBeerItemArrayList.get(position).keyword
                            val keyword2 = SixBeerItemArrayList.get(position).keyword1
                            val keyword3 = SixBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.seventhCategory.setOnClickListener {
            category = "무알콜"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.INVISIBLE
            binding.thirdRecycler.visibility = View.INVISIBLE
            binding.fourthRecycler.visibility = View.INVISIBLE
            binding.fifthRecycler.visibility = View.INVISIBLE
            binding.sixthRecycler.visibility = View.INVISIBLE
            binding.sevenRecycler.visibility = View.INVISIBLE
            binding.eigthRecycler.visibility = View.VISIBLE
            binding.nineRecycler.visibility = View.INVISIBLE

            binding.firstCategory.isSelected = false
            binding.secondCategory.isSelected = false
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = true
            binding.eightCategory.isSelected = false


            for (i in 0..58){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SevenBeerItemArrayList.add(SevenBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sevenBeerAdapter = SevenBeerAdapter(this,SevenBeerItemArrayList)
                    binding.eigthRecycler.adapter = sevenBeerAdapter
                    sevenBeerAdapter.setItemClickListener(object : SevenBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SevenBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SevenBeerItemArrayList.get(position).keyword
                            val keyword2 = SevenBeerItemArrayList.get(position).keyword1
                            val keyword3 = SevenBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.eightCategory.setOnClickListener {
            category = "흑맥주"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.INVISIBLE
            binding.thirdRecycler.visibility = View.INVISIBLE
            binding.fourthRecycler.visibility = View.INVISIBLE
            binding.fifthRecycler.visibility = View.INVISIBLE
            binding.sixthRecycler.visibility = View.INVISIBLE
            binding.sevenRecycler.visibility = View.INVISIBLE
            binding.eigthRecycler.visibility = View.INVISIBLE
            binding.nineRecycler.visibility = View.VISIBLE

            binding.firstCategory.isSelected = false
            binding.secondCategory.isSelected = false
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = true


            for (i in 0..58){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    EightBeerItemArrayList.add(EightBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    eigthBeerAdapter = EightBeerAdapter(this,EightBeerItemArrayList)
                    binding.secondRecycler.adapter = eigthBeerAdapter
                    eigthBeerAdapter.setItemClickListener(object : EightBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = EightBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = EightBeerItemArrayList.get(position).keyword
                            val keyword2 = EightBeerItemArrayList.get(position).keyword1
                            val keyword3 = EightBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SecondItemService(this@SecondActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }

    }

    override fun onGetItemFailure(message: String) {
        showCustomToast("오류:$message")
    }

    override fun onPostItemIDSuccess(response: ItemPostResponse) {
        Log.d("확인",response.statusCode.toString())
    }

    override fun onPostItemIdFailure(message: String) {
        showCustomToast("오류:$message")
    }

}