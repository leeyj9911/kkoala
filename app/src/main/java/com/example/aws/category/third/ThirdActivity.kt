package com.example.aws.category.third

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
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivityThirdBinding

data class ThirdItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String)
data class FirstTraditionalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keword1:String,val keyword2:String)
data class SecondTraditionalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keword1:String,val keyword2:String)
data class ThirdTraditionalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keword1:String,val keyword2:String)
data class FourthTraditionalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keword1:String,val keyword2:String)
data class FifthTraditionalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keword1:String,val keyword2:String)
data class SixTraditionalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keword1:String,val keyword2:String)
data class SevenTraditionalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keword1:String,val keyword2:String)
data class EightTraditionalItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String,val keword1:String,val keyword2:String)


class ThirdActivity : BaseActivity<ActivityThirdBinding>(ActivityThirdBinding::inflate),ThirdCategoryActivityView,ThirdItemActivityView {
    val ThirdItemArrayList = ArrayList<ThirdItemData>()
    val FirstTraditionalItemArrayList = ArrayList<FirstTraditionalItemData>()
    val SecondTraditionalArrayList = ArrayList<SecondTraditionalItemData>()
    val ThirdTraditionalItemArrayList = ArrayList<ThirdTraditionalItemData>()
    val FourthTraditionalItemArrayList = ArrayList<FourthTraditionalItemData>()
    val FifthTraditionalItemArrayList = ArrayList<FifthTraditionalItemData>()
    val SixTraditionalItemArrayList = ArrayList<SixTraditionalItemData>()
    val SevenTraditionalItemArrayList = ArrayList<SevenTraditionalItemData>()
    val EightTraditionalItemArrayList = ArrayList<EightTraditionalItemData>()
    var category = ""
    var count = 0
    private lateinit var thirdAdapter: ThirdAdapter
    private lateinit var firstTraditionalAdapter:FirstTranditionalAdapter
    private lateinit var secondTraditionalAdapter:SecondTraditionalAdapter
    private lateinit var thirdTraditionalAdapter:ThirdTraditionalAdapter
    private lateinit var fourthTraditionalAdapter:FourthTraditionalAdapter
    private lateinit var fifthTraditionalAdapter:FifthTraditionalAdapter
    private lateinit var sixTraditionalAdapter:SixTraditionalAdapter
    private lateinit var sevenTraditionalAdapter:SevenTraditionalAdapter
    private lateinit var eigthTraditionalAdapter:EightTraditionalAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var  TYPE1_1 = 1
        ThirdCategoryService(this).tryGetItem(TYPE1_1)

    }

    override fun onGetItemSuccess(response: ItemCategoryResponse) {
        Log.d("결과",response.Count.toString())

        val link = "https://kkoalabucket.s3.ap-northeast-1.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5%E1%84%89%E1%85%AE%E1%84%8C%E1%85%B5%E1%86%B8/%E1%84%80%E1%85%AE%E1%86%A8%E1%84%89%E1%85%A1%E1%86%AB%E1%84%86%E1%85%A2%E1%86%A8%E1%84%8C%E1%85%AE"

        for (i in 0..48){
            val imgID = (response.Items[i].ITEM_ID).toString()
            val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
            val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"
            ThirdItemArrayList.add(ThirdItemData(alcoholImg,""+response.Items[i].ITEM_NAME.S,""+response.Items[i].ITEM_TYPE4.S+"원",""+response.Items[i].EVENT_VALUE.S,""+response.Items[i].KEYWORD1.S))
        }
        thirdAdapter = ThirdAdapter(this,ThirdItemArrayList)
        binding.firstRecycler.adapter = thirdAdapter
        thirdAdapter.setItemClickListener(object : ThirdAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val ITEM_ID = response.Items[position].ITEM_ID.S
                // 키워드
                val keyword1 = response.Items[position].KEYWORD1.S
                val keyword2 = response.Items[position].KEYWORD2.S
                val keyword3 = response.Items[position].KEYWORD3.S
                Log.d("아이템아이디",ITEM_ID)
                val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                intent.putExtra("ITEM_ID",ITEM_ID)
                intent.putExtra("keyword1",keyword1)
                intent.putExtra("keyword2",keyword2)
                intent.putExtra("keyword3",keyword3)
                startActivity(intent)
                val postItem = PostItem(ITEM_ID = ITEM_ID)
                ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
            }
        })
        binding.firstCategory.setOnClickListener {
            category = "구수한"
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
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FirstTraditionalItemArrayList.add(FirstTraditionalItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    firstTraditionalAdapter = FirstTranditionalAdapter(this,FirstTraditionalItemArrayList)
                    binding.secondRecycler.adapter = firstTraditionalAdapter
                    firstTraditionalAdapter.setItemClickListener(object : FirstTranditionalAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FirstTraditionalItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FirstTraditionalItemArrayList.get(position).keyword
                            val keyword2 = FirstTraditionalItemArrayList.get(position).keword1
                            val keyword3 = FirstTraditionalItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.secondCategory.setOnClickListener {
            category = "깔끔한"
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
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SecondTraditionalArrayList.add(SecondTraditionalItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    secondTraditionalAdapter = SecondTraditionalAdapter(this,SecondTraditionalArrayList)
                    binding.thirdRecycler.adapter = secondTraditionalAdapter
                    secondTraditionalAdapter.setItemClickListener(object : SecondTraditionalAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SecondTraditionalArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SecondTraditionalArrayList.get(position).keyword
                            val keyword2 = SecondTraditionalArrayList.get(position).keword1
                            val keyword3 = SecondTraditionalArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.thirdCategory.setOnClickListener {
            category = "부드러운"
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
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    ThirdTraditionalItemArrayList.add(ThirdTraditionalItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    thirdTraditionalAdapter = ThirdTraditionalAdapter(this,ThirdTraditionalItemArrayList)
                    binding.fourthRecycler.adapter = thirdTraditionalAdapter
                    thirdTraditionalAdapter.setItemClickListener(object : ThirdTraditionalAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = ThirdTraditionalItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = ThirdTraditionalItemArrayList.get(position).keyword
                            val keyword2 = ThirdTraditionalItemArrayList.get(position).keword1
                            val keyword3 = ThirdTraditionalItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fourthCategory.setOnClickListener {
            category = "이색전통주"
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
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FourthTraditionalItemArrayList.add(FourthTraditionalItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fourthTraditionalAdapter = FourthTraditionalAdapter(this,FourthTraditionalItemArrayList)
                    binding.fifthRecycler.adapter = fourthTraditionalAdapter
                    fourthTraditionalAdapter.setItemClickListener(object : FourthTraditionalAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FourthTraditionalItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FourthTraditionalItemArrayList.get(position).keyword
                            val keyword2 = FourthTraditionalItemArrayList.get(position).keword1
                            val keyword3 = FourthTraditionalItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fifthCategory.setOnClickListener {
            category = "홈술"
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
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FifthTraditionalItemArrayList.add(FifthTraditionalItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fifthTraditionalAdapter = FifthTraditionalAdapter(this,FifthTraditionalItemArrayList)
                    binding.sixthRecycler.adapter = fifthTraditionalAdapter
                    fifthTraditionalAdapter.setItemClickListener(object : FifthTraditionalAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FifthTraditionalItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FifthTraditionalItemArrayList.get(position).keyword
                            val keyword2 = FifthTraditionalItemArrayList.get(position).keword1
                            val keyword3 = FifthTraditionalItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.sixthCategory.setOnClickListener {
            category = "선물"
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
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SixTraditionalItemArrayList.add(SixTraditionalItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sixTraditionalAdapter = SixTraditionalAdapter(this,SixTraditionalItemArrayList)
                    binding.sevenRecycler.adapter = sixTraditionalAdapter
                    sixTraditionalAdapter.setItemClickListener(object : SixTraditionalAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SixTraditionalItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SixTraditionalItemArrayList.get(position).keyword
                            val keyword2 = SixTraditionalItemArrayList.get(position).keword1
                            val keyword3 = SixTraditionalItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.seventhCategory.setOnClickListener {
            category = "무감미료"
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
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SevenTraditionalItemArrayList.add(SevenTraditionalItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sevenTraditionalAdapter = SevenTraditionalAdapter(this,SevenTraditionalItemArrayList)
                    binding.eigthRecycler.adapter = sevenTraditionalAdapter
                    sevenTraditionalAdapter.setItemClickListener(object : SevenTraditionalAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SevenTraditionalItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SevenTraditionalItemArrayList.get(position).keyword
                            val keyword2 = SevenTraditionalItemArrayList.get(position).keword1
                            val keyword3 = SevenTraditionalItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.eightCategory.setOnClickListener {
            category = "달달한"
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
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.png"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    EightTraditionalItemArrayList.add(EightTraditionalItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    eigthTraditionalAdapter = EightTraditionalAdapter(this,EightTraditionalItemArrayList)
                    binding.secondRecycler.adapter = eigthTraditionalAdapter
                    eigthTraditionalAdapter.setItemClickListener(object : EightTraditionalAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = EightTraditionalItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = EightTraditionalItemArrayList.get(position).keyword
                            val keyword2 = EightTraditionalItemArrayList.get(position).keword1
                            val keyword3 = EightTraditionalItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@ThirdActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ThirdItemService(this@ThirdActivity).tryPostSign(postItem)// 아이디 넘기기.
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