package com.example.aws.category.first

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.aws.DetailActivity
import com.example.aws.R
import com.example.aws.category.ITEM.ItemPostResponse
import com.example.aws.category.ITEM.PostItem
import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.category.third.ThirdAdapter
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivityFirstBinding
import com.example.aws.src.main.search.ItemCategoryFragmentView
import com.example.aws.src.main.search.ItemCategoryService

data class FirstItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String)//소주데이터
data class FirstSecondItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String,val ITEM_ID : String,val keyword2:String,val keyword1 : String)//첫번째 카테고리
data class ThirdSojuItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String,val ITEM_ID: String,val keyword2:String,val keyword1 : String)//두번째 카테고리
data class FourthSojuItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String,val ITEM_ID: String,val keyword2:String,val keyword1 : String)//세번째 카테고리
data class FifthSojuItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String,val ITEM_ID: String,val keyword2:String,val keyword1 : String)//네번째 카테고리
data class SixSojuItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String,val ITEM_ID: String,val keyword2:String,val keyword1 : String)//다섯번째 카테고리
data class SevenSojuItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String,val ITEM_ID: String,val keyword2:String,val keyword1 : String)//여섯번째 카테고리
data class EigthSojuItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String,val ITEM_ID: String,val keyword2:String,val keyword1 : String)//일곱번째 카테고리
data class NineSojuItemData(val itemim : String, val name:String, val price: String, val star:String,val review:String,val ITEM_ID: String,val keyword2:String,val keyword1 : String)//여덜번째 카테고리

class firstActivity : BaseActivity<ActivityFirstBinding>(ActivityFirstBinding::inflate),ItemCategoryFragmentView ,ItemActivityView{
    val FirstItemArrayList = ArrayList<FirstItemData>()
    val FirstSecondItemArrayList = ArrayList<FirstSecondItemData>()
    val ThirdSojuItemArrayList = ArrayList<ThirdSojuItemData>()
    val FourthSojuItemArrayList = ArrayList<FourthSojuItemData>()
    val FifthSojuItemArrayList = ArrayList<FifthSojuItemData>()
    val SixthSojuItemArrayList = ArrayList<SixSojuItemData>()
    val SevenSojuItemArrayList = ArrayList<SevenSojuItemData>()
    val EigthSojuItemArrayList = ArrayList<EigthSojuItemData>()
    val NineSojuItemArrayList = ArrayList<NineSojuItemData>()

    var first : String= " "
    var second: String = " "
    var count =0
    private lateinit var firstAdapter: FirstAdapter
    private lateinit var secondAdapter: FirstSecondAdapter
    private lateinit var thirdAdapter : ThirdSojuAdapter
    private lateinit var fourthAdapter : FourthSojuAdapter
    private lateinit var fifthAdapter : FifthSojuAdapter
    private lateinit var sixAdapter : SixSojuAdapter
    private lateinit var sevenAdapter : SevenSojuAdapter
    private lateinit var eigthAdapter: EigthSojuAdapter
    private lateinit var nineAdapter : NineSojuAdapter
    var TYPE1_1 =4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ItemCategoryService(this).tryGetItem(TYPE1_1) // 소주 데이터 가져오기

    }


    override fun onGetItemSuccess(response: ItemCategoryResponse) {
        Log.d("확인",response.Count.toString())

        val link = "https://kkoalabucket.s3.ap-northeast-1.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5%E1%84%89%E1%85%AE%E1%84%8C%E1%85%B5%E1%86%B8/%E1%84%89%E1%85%A9%E1%84%8C%E1%85%AE"

        for(i in 0..25){
            val imgID = (response.Items[i].ITEM_ID).toString()
            val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
            val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"
            FirstItemArrayList.add(FirstItemData(alcoholImg,""+response.Items[i].ITEM_NAME.S,""+response.Items[i].ITEM_TYPE4.S+"원",""+response.Items[i].EVENT_VALUE.S,"#"+response.Items[i].KEYWORD1.S))

        }
        firstAdapter = FirstAdapter(this,FirstItemArrayList)
        binding.firstRecycler.adapter = firstAdapter
        firstAdapter.setItemClickListener(object : FirstAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val ITEM_ID = response.Items[position].ITEM_ID.S
                // 키워드
                val keyword1 = response.Items[position].KEYWORD1.S
                val keyword2 = response.Items[position].KEYWORD2.S
                val keyword3 = response.Items[position].KEYWORD3.S
                Log.d("아이템아이디",ITEM_ID)
                val intent = Intent(this@firstActivity,DetailActivity::class.java)
                intent.putExtra("ITEM_ID",ITEM_ID)
                intent.putExtra("keyword1",keyword1)
                intent.putExtra("keyword2",keyword2)
                intent.putExtra("keyword3",keyword3)
                startActivity(intent)
                val postItem = PostItem(ITEM_ID = ITEM_ID)
                ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
            }
        })// 현재 클릭했을 때 데이터 받아오기.

        // 처음 소주 데이터 띄워주기

        //첫번쨰 카테고리 술 띄워주기
        binding.firstCategory.setOnClickListener {
            first = "지역술"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.INVISIBLE
            binding.thirdRecycler.visibility = View.VISIBLE
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


            for (i in 0..25){
                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (first == response.Items[i].KEYWORD1.S ||first ==response.Items[i].KEYWORD2.S||first==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    ThirdSojuItemArrayList.add(ThirdSojuItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    thirdAdapter = ThirdSojuAdapter(this,ThirdSojuItemArrayList)
                    binding.thirdRecycler.adapter = thirdAdapter
                    thirdAdapter.setItemClickListener(object : ThirdSojuAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = ThirdSojuItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = ThirdSojuItemArrayList.get(position).review
                            val keyword2 = ThirdSojuItemArrayList.get(position).keyword1
                            val keyword3 = ThirdSojuItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@firstActivity,DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }
            // 현재 클릭했을 때 데이터 받아오기.
        }
        binding.secondCategory.setOnClickListener {
            first="깔끔한"
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
            binding.secondCategory.isSelected = true
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false
            for (i in 0..25){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (first == response.Items[i].KEYWORD1.S || first ==response.Items[i].KEYWORD2.S||first==response.Items[i].KEYWORD3.S){
                    count=i
                    Log.d("count",count.toString())
                    Log.d("제발",response.Items[count].ITEM_NAME.S)
                    FourthSojuItemArrayList.add(FourthSojuItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fourthAdapter = FourthSojuAdapter(this,FourthSojuItemArrayList)
                    binding.fourthRecycler.adapter = fourthAdapter
                    fourthAdapter.setItemClickListener(object : FourthSojuAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FourthSojuItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FourthSojuItemArrayList.get(position).review
                            val keyword2 = FourthSojuItemArrayList.get(position).keyword1
                            val keyword3 = FourthSojuItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@firstActivity,DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })

                }
            }
          // 현재 클릭했을 때 데이터 받아오기.
        }
        binding.thirdCategory.setOnClickListener {
            first="부드러운"
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
            binding.thirdCategory.isSelected = true
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false
            for (i in 0..25){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (first == response.Items[i].KEYWORD1.S || first ==response.Items[i].KEYWORD2.S||first==response.Items[i].KEYWORD3.S){
                    count=i
                    Log.d("count",count.toString())
                    Log.d("제발",response.Items[count].ITEM_NAME.S)
                    FifthSojuItemArrayList.add(FifthSojuItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fifthAdapter = FifthSojuAdapter(this,FifthSojuItemArrayList)
                    binding.fifthRecycler.adapter = fifthAdapter
                    fifthAdapter.setItemClickListener(object : FifthSojuAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FifthSojuItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FifthSojuItemArrayList.get(position).review
                            val keyword2 = FifthSojuItemArrayList.get(position).keyword1
                            val keyword3 = FifthSojuItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@firstActivity,DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })

                }
            }
           // 현재 클릭했을 때 데이터 받아오기.
        }
        binding.fourthCategory.setOnClickListener {
            first="이색적인"
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
            binding.fourthCategory.isSelected = true
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false
            for (i in 0..25){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (first == response.Items[i].KEYWORD1.S || first ==response.Items[i].KEYWORD2.S||first==response.Items[i].KEYWORD3.S){
                    count=i
                    Log.d("count",count.toString())
                    Log.d("제발",response.Items[count].ITEM_NAME.S)
                    SixthSojuItemArrayList.add(SixSojuItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sixAdapter = SixSojuAdapter(this,SixthSojuItemArrayList)
                    binding.sixthRecycler.adapter = sixAdapter
                    sixAdapter.setItemClickListener(object : SixSojuAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SixthSojuItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SixthSojuItemArrayList.get(position).review
                            val keyword2 = SixthSojuItemArrayList.get(position).keyword1
                            val keyword3 = SixthSojuItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@firstActivity,DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })

                }
            }
         // 현재 클릭했을 때 데이터 받아오기.
        }
        binding.fifthCategory.setOnClickListener {
            first="과일류"
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
            binding.fifthCategory.isSelected = true
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false
            for (i in 0..25){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (first == response.Items[i].KEYWORD1.S || first ==response.Items[i].KEYWORD2.S||first==response.Items[i].KEYWORD3.S){
                    count=i
                    Log.d("count",count.toString())
                    Log.d("제발",response.Items[count].ITEM_NAME.S)
                    SevenSojuItemArrayList.add(SevenSojuItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sevenAdapter = SevenSojuAdapter(this,SevenSojuItemArrayList)
                    binding.sevenRecycler.adapter = sevenAdapter

                    sevenAdapter.setItemClickListener(object : SevenSojuAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SevenSojuItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SevenSojuItemArrayList.get(position).review
                            val keyword2 = SevenSojuItemArrayList.get(position).keyword1
                            val keyword3 = SevenSojuItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@firstActivity,DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })// 현재 클릭했을 때 데이터 받아오기.

                }
            }
        }
        binding.sixthCategory.setOnClickListener {
            first="파티"
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
            binding.sixthCategory.isSelected = true
            binding.seventhCategory.isSelected = false
            binding.eightCategory.isSelected = false
            for (i in 0..25){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (first == response.Items[i].KEYWORD1.S || first ==response.Items[i].KEYWORD2.S||first==response.Items[i].KEYWORD3.S){
                    count=i
                    Log.d("count",count.toString())
                    Log.d("제발",response.Items[count].ITEM_NAME.S)
                    EigthSojuItemArrayList.add(EigthSojuItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    eigthAdapter = EigthSojuAdapter(this,EigthSojuItemArrayList)
                    binding.eigthRecycler.adapter = eigthAdapter
                    eigthAdapter.setItemClickListener(object : EigthSojuAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = EigthSojuItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = EigthSojuItemArrayList.get(position).review
                            val keyword2 = EigthSojuItemArrayList.get(position).keyword1
                            val keyword3 = EigthSojuItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@firstActivity,DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })

                }
            }

        }

        binding.seventhCategory.setOnClickListener {
            first="혼술"
            binding.firstRecycler.visibility = View.INVISIBLE
            binding.secondRecycler.visibility = View.VISIBLE
            binding.thirdRecycler.visibility = View.INVISIBLE
            binding.fourthRecycler.visibility = View.INVISIBLE
            binding.fifthRecycler.visibility = View.INVISIBLE
            binding.sixthRecycler.visibility = View.INVISIBLE
            binding.sevenRecycler.visibility = View.INVISIBLE
            binding.eigthRecycler.visibility = View.INVISIBLE
            binding.nineRecycler.visibility = View.INVISIBLE
            binding.firstCategory.isSelected = false
            binding.secondCategory.isSelected = false
            binding.thirdCategory.isSelected = false
            binding.fourthCategory.isSelected = false
            binding.fifthCategory.isSelected = false
            binding.sixthCategory.isSelected = false
            binding.seventhCategory.isSelected = true
            binding.eightCategory.isSelected = false
            for (i in 0..25){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (first == response.Items[i].KEYWORD1.S || first ==response.Items[i].KEYWORD2.S||first==response.Items[i].KEYWORD3.S){
                    count=i
                    Log.d("count",count.toString())
                    Log.d("제발",response.Items[count].ITEM_NAME.S)
                    FirstSecondItemArrayList.add(FirstSecondItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    secondAdapter = FirstSecondAdapter(this,FirstSecondItemArrayList)
                    binding.secondRecycler.adapter = secondAdapter
                    secondAdapter.setItemClickListener(object : FirstSecondAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FirstSecondItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FirstSecondItemArrayList.get(position).review
                            val keyword2 = FirstSecondItemArrayList.get(position).keyword1
                            val keyword3 = FirstSecondItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@firstActivity,DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })// 현재 클릭했을 때 데이터 받아오기.

                }
            }

        }
        binding.eightCategory.setOnClickListener {
            first="진한맛"
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
            for (i in 0..25){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (first == response.Items[i].KEYWORD1.S || first ==response.Items[i].KEYWORD2.S||first==response.Items[i].KEYWORD3.S){
                    count=i
                    Log.d("count",count.toString())
                    Log.d("제발",response.Items[count].ITEM_NAME.S)
                    NineSojuItemArrayList.add(NineSojuItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    nineAdapter = NineSojuAdapter(this,NineSojuItemArrayList)
                    binding.nineRecycler.adapter = nineAdapter
                    nineAdapter.setItemClickListener(object : NineSojuAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = NineSojuItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = NineSojuItemArrayList.get(position).review
                            val keyword2 = NineSojuItemArrayList.get(position).keyword1
                            val keyword3 = NineSojuItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@firstActivity,DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            ItemService(this@firstActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })// 현재 클릭했을 때 데이터 받아오기.

                }
            }

        }







    }

    override fun onGetItemFailure(message: String) {
        showCustomToast("오류:$message")
    }

    override fun onPostItemIDSuccess(response: ItemPostResponse) {
        Log.d("결과는?",response.statusCode.toString())
    }

    override fun onPostItemIdFailure(message: String) {
        showCustomToast("오류:$message")
    }
}
