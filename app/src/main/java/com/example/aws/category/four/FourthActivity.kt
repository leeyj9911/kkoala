package com.example.aws.category.four

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.aws.DetailActivity
import com.example.aws.R
import com.example.aws.category.ITEM.ItemPostResponse
import com.example.aws.category.ITEM.PostItem
import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.category.second.SecondItemService
import com.example.aws.category.second.SevenBeerAdapter
import com.example.aws.category.third.*
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivityFourthBinding

data class FourthItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String)
data class FirstKBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String, val keyword2:String)
data class SecondKBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String, val keyword2:String)
data class ThirdKBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String, val keyword2:String)
data class FourthKBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String, val keyword2:String)
data class FifthKBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String, val keyword2:String)
data class SixKBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String, val keyword2:String)
data class SevenKBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String, val keyword2:String)
data class EightKBeerItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keyword1:String, val keyword2:String)



class FourthActivity : BaseActivity<ActivityFourthBinding>(ActivityFourthBinding::inflate),FourthCategoryActivityView,FourthItemActivityView {
    val FourthItemArrayList = ArrayList<FourthItemData>()
    val FirstKBeerItemArrayList = ArrayList<FirstKBeerItemData>()
    val SecondKBeerArrayList = ArrayList<SecondKBeerItemData>()
    val ThirdKBeerItemArrayList = ArrayList<ThirdKBeerItemData>()
    val FourthKBeerItemArrayList = ArrayList<FourthKBeerItemData>()
    val FifthKBeerItemArrayList = ArrayList<FifthKBeerItemData>()
    val SixKBeerItemArrayList = ArrayList<SixKBeerItemData>()
    val SevenKBeerItemArrayList = ArrayList<SevenKBeerItemData>()
    val EightKBeerItemArrayList = ArrayList<EightKBeerItemData>()
    private lateinit var fourthAdapter: FourthAdapter
    private lateinit var firstKBeerAdapter:FirstKBeerAdapter
    private lateinit var secondKBeerAdapter:SecondKBeerAdapter
    private lateinit var thirdKBeerAdapter:ThirdKBeerAdapter
    private lateinit var fourthKBeerAdapter:FourthKBeerAdapter
    private lateinit var fifthKBeerAdapter:FifthKBeerAdapter
    private lateinit var sixKBeerAdapter:SixKBeerAdapter
    private lateinit var sevenKBeerAdapter:SevenKBeerAdapter
    private lateinit var eigthKBeerAdapter:EightKBeerAdapter
    var category = ""
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var  TYPE1_1 = 3
        FourthCategoryService(this).tryGetItem(TYPE1_1)
    }

    override fun onGetItemSuccess(response: ItemCategoryResponse) {
        Log.d("결과",response.Count.toString())

        val link = "https://kkoalabucket.s3.ap-northeast-1.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5%E1%84%89%E1%85%AE%E1%84%8C%E1%85%B5%E1%86%B8/%E1%84%80%E1%85%AE%E1%86%A8%E1%84%89%E1%85%A1%E1%86%AB%E1%84%86%E1%85%A2%E1%86%A8%E1%84%8C%E1%85%AE"

        for (i in 0..33){
            val imgID = (response.Items[i].ITEM_ID).toString()
            val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
            val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"
            FourthItemArrayList.add(FourthItemData(alcoholImg,""+response.Items[i].ITEM_NAME.S,""+response.Items[i].ITEM_TYPE4.S+"원",""+response.Items[i].EVENT_VALUE.S,""+response.Items[i].KEYWORD1.S))
        }
        fourthAdapter = FourthAdapter(this,FourthItemArrayList)
        binding.firstRecycler.adapter = fourthAdapter
        fourthAdapter.setItemClickListener(object : FourthAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val ITEM_ID = response.Items[position].ITEM_ID.S
                // 키워드
                val keyword1 = response.Items[position].KEYWORD1.S
                val keyword2 = response.Items[position].KEYWORD2.S
                val keyword3 = response.Items[position].KEYWORD3.S
                Log.d("아이템아이디",ITEM_ID)
                val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                intent.putExtra("ITEM_ID",ITEM_ID)
                intent.putExtra("keyword1",keyword1)
                intent.putExtra("keyword2",keyword2)
                intent.putExtra("keyword3",keyword3)
                startActivity(intent)
                val postItem = PostItem(ITEM_ID = ITEM_ID)
                FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
            }
        })
        binding.firstCategory.setOnClickListener {
            category = "깔끔한"
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


            for (i in 0..33){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FirstKBeerItemArrayList.add(FirstKBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    firstKBeerAdapter = FirstKBeerAdapter(this,FirstKBeerItemArrayList)
                    binding.secondRecycler.adapter = firstKBeerAdapter
                    firstKBeerAdapter.setItemClickListener(object : FirstKBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FirstKBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FirstKBeerItemArrayList.get(position).keyword
                            val keyword2 = FirstKBeerItemArrayList.get(position).keyword1
                            val keyword3 = FirstKBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.secondCategory.setOnClickListener {
            category = "탄산"
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


            for (i in 0..33){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SecondKBeerArrayList.add(SecondKBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    secondKBeerAdapter = SecondKBeerAdapter(this,SecondKBeerArrayList)
                    binding.thirdRecycler.adapter = secondKBeerAdapter
                    secondKBeerAdapter.setItemClickListener(object : SecondKBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SecondKBeerArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SecondKBeerArrayList.get(position).keyword
                            val keyword2 = SecondKBeerArrayList.get(position).keyword1
                            val keyword3 = SecondKBeerArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.thirdCategory.setOnClickListener {
            category = "시원함"
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


            for (i in 0..33){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    ThirdKBeerItemArrayList.add(ThirdKBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    thirdKBeerAdapter = ThirdKBeerAdapter(this,ThirdKBeerItemArrayList)
                    binding.fourthRecycler.adapter = thirdKBeerAdapter
                    thirdKBeerAdapter.setItemClickListener(object : ThirdKBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = ThirdKBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = ThirdKBeerItemArrayList.get(position).keyword
                            val keyword2 = ThirdKBeerItemArrayList.get(position).keyword1
                            val keyword3 = ThirdKBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fourthCategory.setOnClickListener {
            category = "드라이"
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


            for (i in 0..33){
                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FourthKBeerItemArrayList.add(FourthKBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fourthKBeerAdapter = FourthKBeerAdapter(this,FourthKBeerItemArrayList)
                    binding.fifthRecycler.adapter = fourthKBeerAdapter
                    fourthKBeerAdapter.setItemClickListener(object : FourthKBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FourthKBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FourthKBeerItemArrayList.get(position).keyword
                            val keyword2 = FourthKBeerItemArrayList.get(position).keyword1
                            val keyword3 = FourthKBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fifthCategory.setOnClickListener {
            category = "목넘김"
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


            for (i in 0..33){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FifthKBeerItemArrayList.add(FifthKBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fifthKBeerAdapter = FifthKBeerAdapter(this,FifthKBeerItemArrayList)
                    binding.sixthRecycler.adapter = fifthKBeerAdapter
                    fifthKBeerAdapter.setItemClickListener(object : FifthKBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FifthKBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FifthKBeerItemArrayList.get(position).keyword
                            val keyword2 = FifthKBeerItemArrayList.get(position).keyword1
                            val keyword3 = FifthKBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.sixthCategory.setOnClickListener {
            category = "청량감"
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


            for (i in 0..33){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SixKBeerItemArrayList.add(SixKBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sixKBeerAdapter = SixKBeerAdapter(this,SixKBeerItemArrayList)
                    binding.sevenRecycler.adapter = sixKBeerAdapter
                    sixKBeerAdapter.setItemClickListener(object : SixKBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SixKBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SixKBeerItemArrayList.get(position).keyword
                            val keyword2 = SixKBeerItemArrayList.get(position).keyword1
                            val keyword3 = SixKBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.seventhCategory.setOnClickListener {
            category = "고소함"
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


            for (i in 0..33){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SevenKBeerItemArrayList.add(SevenKBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sevenKBeerAdapter = SevenKBeerAdapter(this,SevenKBeerItemArrayList)
                    binding.eigthRecycler.adapter = sevenKBeerAdapter
                    sevenKBeerAdapter.setItemClickListener(object : SevenKBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SevenKBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SevenKBeerItemArrayList.get(position).keyword
                            val keyword2 = SevenKBeerItemArrayList.get(position).keyword1
                            val keyword3 = SevenKBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.eightCategory.setOnClickListener {
            category = "바디감"
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


            for (i in 0..33){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    EightKBeerItemArrayList.add(EightKBeerItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    eigthKBeerAdapter = EightKBeerAdapter(this,EightKBeerItemArrayList)
                    binding.secondRecycler.adapter = eigthKBeerAdapter
                    eigthKBeerAdapter.setItemClickListener(object : EightKBeerAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = EightKBeerItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = EightKBeerItemArrayList.get(position).keyword
                            val keyword2 = EightKBeerItemArrayList.get(position).keyword1
                            val keyword3 = EightKBeerItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FourthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FourthItemService(this@FourthActivity).tryPostSign(postItem)// 아이디 넘기기.
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