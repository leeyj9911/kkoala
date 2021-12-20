package com.example.aws.category.five

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.aws.DetailActivity
import com.example.aws.R
import com.example.aws.category.ITEM.ItemPostResponse
import com.example.aws.category.ITEM.PostItem
import com.example.aws.category.four.*

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.category.second.SecondItemService
import com.example.aws.category.second.SevenBeerAdapter
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivityFifthBinding

data class FifthItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String)
data class FirstWineItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class SecondWineItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class ThirdWineItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class FourthWineItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class FifthWineItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class SixWineItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class SevenWineItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class EightWineItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)


class FifthActivity : BaseActivity<ActivityFifthBinding>(ActivityFifthBinding::inflate),FifthCategoryActivityView,FifthItemActivityView {
    val FifthItemArrayList = ArrayList<FifthItemData>()
    val FirstWineItemArrayList = ArrayList<FirstWineItemData>()
    val SecondWineItemArrayList = ArrayList<SecondWineItemData>()
    val ThirdWineItemArrayList = ArrayList<ThirdWineItemData>()
    val FourthWineItemArrayList = ArrayList<FourthWineItemData>()
    val FifthWineItemArrayList = ArrayList<FifthWineItemData>()
    val SixWineItemArrayList = ArrayList<SixWineItemData>()
    val SevenWineItemArrayList = ArrayList<SevenWineItemData>()
    val EightWineItemArrayList = ArrayList<EightWineItemData>()
    private lateinit var fifthAdapter: FifthAdapter
    private lateinit var firstWineAdapter:FirstWineAdapter
    private lateinit var secondWineAdapter:SecondWineAdapter
    private lateinit var thirdWineAdapter:ThirdWineAdapter
    private lateinit var fourthWineAdapter:FourthWineAdapter
    private lateinit var fifthWineAdapter:FifthWineAdapter
    private lateinit var sixWineAdapter:SixWineAdapter
    private lateinit var sevenWineAdapter:SevenWineAdapter
    private lateinit var eigthWineAdapter:EightWineAdapter
    var category = ""
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var  TYPE1_1 = 6
        FifthCategoryService(this).tryGetItem(TYPE1_1)

    }

    override fun onGetItemSuccess(response: ItemCategoryResponse) {
        Log.d("결과",response.Count.toString())

        val link = "https://kkoalabucket.s3.ap-northeast-1.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5%E1%84%89%E1%85%AE%E1%84%8C%E1%85%B5%E1%86%B8/%E1%84%8B%E1%85%AA%E1%84%8B%E1%85%B5%E1%86%AB"

        for (i in 0..48){
            val imgID = (response.Items[i].ITEM_ID).toString()
            val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
            val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"
            FifthItemArrayList.add(FifthItemData(alcoholImg,""+response.Items[i].ITEM_NAME.S,""+response.Items[i].ITEM_TYPE4.S+"원",""+response.Items[i].EVENT_VALUE.S,""+response.Items[i].KEYWORD1.S))
        }
        fifthAdapter = FifthAdapter(this,FifthItemArrayList)
        binding.firstRecycler.adapter = fifthAdapter
        fifthAdapter.setItemClickListener(object : FifthAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val ITEM_ID = response.Items[position].ITEM_ID.S
                // 키워드
                val keyword1 = response.Items[position].KEYWORD1.S
                val keyword2 = response.Items[position].KEYWORD2.S
                val keyword3 = response.Items[position].KEYWORD3.S
                Log.d("아이템아이디",ITEM_ID)
                val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                intent.putExtra("ITEM_ID",ITEM_ID)
                intent.putExtra("keyword1",keyword1)
                intent.putExtra("keyword2",keyword2)
                intent.putExtra("keyword3",keyword3)
                startActivity(intent)
                val postItem = PostItem(ITEM_ID = ITEM_ID)
                FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
            }
        })
        binding.firstCategory.setOnClickListener {
            category = "레드와인"
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


            for (i in 0..48){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FirstWineItemArrayList.add(FirstWineItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    firstWineAdapter = FirstWineAdapter(this,FirstWineItemArrayList)
                    binding.secondRecycler.adapter = firstWineAdapter
                    firstWineAdapter.setItemClickListener(object : FirstWineAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FirstWineItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FirstWineItemArrayList.get(position).keyword
                            val keyword2 = FirstWineItemArrayList.get(position).keyword1
                            val keyword3 = FirstWineItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.secondCategory.setOnClickListener {
            category = "드라이와인"
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


            for (i in 0..48){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SecondWineItemArrayList.add(SecondWineItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    secondWineAdapter = SecondWineAdapter(this,SecondWineItemArrayList)
                    binding.thirdRecycler.adapter = secondWineAdapter
                    secondWineAdapter.setItemClickListener(object : SecondWineAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SecondWineItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SecondWineItemArrayList.get(position).keyword
                            val keyword2 = SecondWineItemArrayList.get(position).keyword1
                            val keyword3 = SecondWineItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.thirdCategory.setOnClickListener {
            category = "화이트와인"
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


            for (i in 0..48){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    ThirdWineItemArrayList.add(ThirdWineItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    thirdWineAdapter = ThirdWineAdapter(this,ThirdWineItemArrayList)
                    binding.fourthRecycler.adapter = thirdWineAdapter
                    thirdWineAdapter.setItemClickListener(object : ThirdWineAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = ThirdWineItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = ThirdWineItemArrayList.get(position).keyword
                            val keyword2 = ThirdWineItemArrayList.get(position).keyword1
                            val keyword3 = ThirdWineItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fourthCategory.setOnClickListener {
            category = "스파클링와인"
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


            for (i in 0..48){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FourthWineItemArrayList.add(FourthWineItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fourthWineAdapter = FourthWineAdapter(this,FourthWineItemArrayList)
                    binding.fifthRecycler.adapter = fourthWineAdapter
                    fourthWineAdapter.setItemClickListener(object : FourthWineAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FourthWineItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FourthWineItemArrayList.get(position).keyword
                            val keyword2 = FourthWineItemArrayList.get(position).keyword1
                            val keyword3 = FourthWineItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fifthCategory.setOnClickListener {
            category = "과일"
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


            for (i in 0..48){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FifthWineItemArrayList.add(FifthWineItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fifthWineAdapter = FifthWineAdapter(this,FifthWineItemArrayList)
                    binding.sixthRecycler.adapter = fifthWineAdapter
                    fifthWineAdapter.setItemClickListener(object : FifthWineAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FifthWineItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FifthWineItemArrayList.get(position).keyword
                            val keyword2 = FifthWineItemArrayList.get(position).keyword1
                            val keyword3 = FifthWineItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.sixthCategory.setOnClickListener {
            category = "기념일"
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


            for (i in 0..48){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SixWineItemArrayList.add(SixWineItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sixWineAdapter = SixWineAdapter(this,SixWineItemArrayList)
                    binding.sevenRecycler.adapter = sixWineAdapter
                    sixWineAdapter.setItemClickListener(object : SixWineAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SixWineItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SixWineItemArrayList.get(position).keyword
                            val keyword2 = SixWineItemArrayList.get(position).keyword1
                            val keyword3 = SixWineItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.seventhCategory.setOnClickListener {
            category = "꽃"
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


            for (i in 0..48){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SevenWineItemArrayList.add(SevenWineItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sevenWineAdapter = SevenWineAdapter(this,SevenWineItemArrayList)
                    binding.eigthRecycler.adapter = sevenWineAdapter
                    sevenWineAdapter.setItemClickListener(object : SevenWineAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SevenWineItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SevenWineItemArrayList.get(position).keyword
                            val keyword2 = SevenWineItemArrayList.get(position).keyword1
                            val keyword3 = SevenWineItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.eightCategory.setOnClickListener {
            category = "입문자용"
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


            for (i in 0..48){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.JPG"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    EightWineItemArrayList.add(EightWineItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    eigthWineAdapter = EightWineAdapter(this,EightWineItemArrayList)
                    binding.secondRecycler.adapter = eigthWineAdapter
                    eigthWineAdapter.setItemClickListener(object : EightWineAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = EightWineItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = EightWineItemArrayList.get(position).keyword
                            val keyword2 = EightWineItemArrayList.get(position).keyword1
                            val keyword3 = EightWineItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@FifthActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            FifthItemService(this@FifthActivity).tryPostSign(postItem)// 아이디 넘기기.
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