package com.example.aws.category.six

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.aws.DetailActivity
import com.example.aws.R
import com.example.aws.category.ITEM.ItemPostResponse
import com.example.aws.category.ITEM.PostItem
import com.example.aws.category.four.*
import com.example.aws.category.model.CategoryResponse
import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.category.second.SecondItemService
import com.example.aws.category.second.SevenBeerAdapter
import com.example.aws.config.BaseActivity
import com.example.aws.databinding.ActivitySixBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class SixItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String)
data class FirstliquorItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class SecondliquorItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class ThirdliquorItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class FourthliquorItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class FifthliquorItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class SixliquorItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class SevenliquorItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)
data class EightliquorItemData(val itemim : String, val name:String, val price: String, val star:String,val keyword:String,val ITEM_ID:String, val keyword1:String, val keyword2:String)


class SixActivity : BaseActivity<ActivitySixBinding>(ActivitySixBinding::inflate),SixCategoryActivityview,SixItemActivityView {
    val SixItemArrayList = ArrayList<SixItemData>()
    val FirstLiquorItemArrayList = ArrayList<FirstliquorItemData>()
    val SecondLiquorArrayList = ArrayList<SecondliquorItemData>()
    val ThirdLiquorItemArrayList = ArrayList<ThirdliquorItemData>()
    val FourthLiquorItemArrayList = ArrayList<FourthliquorItemData>()
    val FifthLiquorItemArrayList = ArrayList<FifthliquorItemData>()
    val SixLiquorItemArrayList = ArrayList<SixliquorItemData>()
    val SevenLiquorItemArrayList = ArrayList<SevenliquorItemData>()
    val EightLiquorItemArrayList = ArrayList<EightliquorItemData>()
    private lateinit var sixAdapter: SixAdapter
    private lateinit var firstLiquorAdapter:FirstLiquorAdapter
    private lateinit var secondLiquorAdapter:SecondLiquorAdapter
    private lateinit var thirdLiquorAdapter:ThirdLiquorAdapter
    private lateinit var fourthLiquorAdapter:FourthLiquorAdapter
    private lateinit var fifthLiquorAdapter:FifthLiquorAdapter
    private lateinit var sixLiquorAdapter:SixLiquorAdapter
    private lateinit var sevenLiquorAdapter:SevenLiquorAdapter
    private lateinit var eigthLiquorAdapter:EightLiquorAdapter
    var category = ""
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  TYPE1_1 = 5
        SixCategoryService(this).tryGetItem(TYPE1_1)

    }

    override fun onGetItemSuccess(response: ItemCategoryResponse) {
        Log.d("결과",response.Count.toString())

        val link = "https://kkoalabucket.s3.ap-northeast-1.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5%E1%84%89%E1%85%AE%E1%84%8C%E1%85%B5%E1%86%B8/%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8C%E1%85%AE"

        for (i in 0..10){
            val imgID = (response.Items[i].ITEM_ID).toString()
            val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
            val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"
            SixItemArrayList.add(SixItemData(alcoholImg,""+response.Items[i].ITEM_NAME.S,""+response.Items[i].ITEM_TYPE4.S+"원",""+response.Items[i].EVENT_VALUE.S,""+response.Items[i].KEYWORD1.S))
        }
        sixAdapter = SixAdapter(this,SixItemArrayList)
        binding.firstRecycler.adapter = sixAdapter
        sixAdapter.setItemClickListener(object : SixAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val ITEM_ID = response.Items[position].ITEM_ID.S
                // 키워드
                val keyword1 = response.Items[position].KEYWORD1.S
                val keyword2 = response.Items[position].KEYWORD2.S
                val keyword3 = response.Items[position].KEYWORD3.S
                Log.d("아이템아이디",ITEM_ID)
                val intent = Intent(this@SixActivity, DetailActivity::class.java)
                intent.putExtra("ITEM_ID",ITEM_ID)
                intent.putExtra("keyword1",keyword1)
                intent.putExtra("keyword2",keyword2)
                intent.putExtra("keyword3",keyword3)
                startActivity(intent)
                val postItem = PostItem(ITEM_ID = ITEM_ID)
                SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
            }
        })
        binding.firstCategory.setOnClickListener {
            category = "드라이"
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


            for (i in 0..10){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FirstLiquorItemArrayList.add(FirstliquorItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    firstLiquorAdapter = FirstLiquorAdapter(this,FirstLiquorItemArrayList)
                    binding.secondRecycler.adapter = firstLiquorAdapter
                    firstLiquorAdapter.setItemClickListener(object : FirstLiquorAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FirstLiquorItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FirstLiquorItemArrayList.get(position).keyword
                            val keyword2 = FirstLiquorItemArrayList.get(position).keyword1
                            val keyword3 = FirstLiquorItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SixActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.secondCategory.setOnClickListener {
            category = "파티"
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


            for (i in 0..10){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SecondLiquorArrayList.add(SecondliquorItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    secondLiquorAdapter = SecondLiquorAdapter(this,SecondLiquorArrayList)
                    binding.thirdRecycler.adapter = secondLiquorAdapter
                    secondLiquorAdapter.setItemClickListener(object : SecondLiquorAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SecondLiquorArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SecondLiquorArrayList.get(position).keyword
                            val keyword2 = SecondLiquorArrayList.get(position).keyword1
                            val keyword3 = SecondLiquorArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SixActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.thirdCategory.setOnClickListener {
            category = "선물"
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


            for (i in 0..10){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    ThirdLiquorItemArrayList.add(ThirdliquorItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    thirdLiquorAdapter = ThirdLiquorAdapter(this,ThirdLiquorItemArrayList)
                    binding.fourthRecycler.adapter = thirdLiquorAdapter
                    thirdLiquorAdapter.setItemClickListener(object : ThirdLiquorAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = ThirdLiquorItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = ThirdLiquorItemArrayList.get(position).keyword
                            val keyword2 = ThirdLiquorItemArrayList.get(position).keyword1
                            val keyword3 = ThirdLiquorItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SixActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fourthCategory.setOnClickListener {
            category = "칵테일"
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


            for (i in 0..10){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FourthLiquorItemArrayList.add(FourthliquorItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fourthLiquorAdapter = FourthLiquorAdapter(this,FourthLiquorItemArrayList)
                    binding.fifthRecycler.adapter = fourthLiquorAdapter
                    fourthLiquorAdapter.setItemClickListener(object : FourthLiquorAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FourthLiquorItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FourthLiquorItemArrayList.get(position).keyword
                            val keyword2 = FourthLiquorItemArrayList.get(position).keyword1
                            val keyword3 = FourthLiquorItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SixActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.fifthCategory.setOnClickListener {
            category = "과일향"
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


            for (i in 0..10){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    FifthLiquorItemArrayList.add(FifthliquorItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    fifthLiquorAdapter = FifthLiquorAdapter(this,FifthLiquorItemArrayList)
                    binding.sixthRecycler.adapter = fifthLiquorAdapter
                    fifthLiquorAdapter.setItemClickListener(object : FifthLiquorAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = FifthLiquorItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = FifthLiquorItemArrayList.get(position).keyword
                            val keyword2 = FifthLiquorItemArrayList.get(position).keyword1
                            val keyword3 = FifthLiquorItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SixActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
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


            for (i in 0..10){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SixLiquorItemArrayList.add(SixliquorItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sixLiquorAdapter = SixLiquorAdapter(this,SixLiquorItemArrayList)
                    binding.sevenRecycler.adapter = sixLiquorAdapter
                    sixLiquorAdapter.setItemClickListener(object : SixLiquorAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SixLiquorItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SixLiquorItemArrayList.get(position).keyword
                            val keyword2 = SixLiquorItemArrayList.get(position).keyword1
                            val keyword3 = SixLiquorItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SixActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.seventhCategory.setOnClickListener {
            category = "집들이"
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


            for (i in 0..10){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    SevenLiquorItemArrayList.add(SevenliquorItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    sevenLiquorAdapter = SevenLiquorAdapter(this,SevenLiquorItemArrayList)
                    binding.eigthRecycler.adapter = sevenLiquorAdapter
                    sevenLiquorAdapter.setItemClickListener(object : SevenLiquorAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = SevenLiquorItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = SevenLiquorItemArrayList.get(position).keyword
                            val keyword2 = SevenLiquorItemArrayList.get(position).keyword1
                            val keyword3 = SevenLiquorItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SixActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
                        }
                    })
                }
            }

        }
        binding.eightCategory.setOnClickListener {
            category = "과일류"
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


            for (i in 0..10){

                val imgID = (response.Items[i].ITEM_ID).toString()
                val imgNum = imgID.subSequence(9, imgID.indexOf(")"))
                val alcoholImg = "${link}/_${imgNum.toString().toInt()}_.jpg"

                if (category == response.Items[i].KEYWORD1.S ||category ==response.Items[i].KEYWORD2.S||category==response.Items[i].KEYWORD3.S){
                    count =i
                    Log.d("first_category",response.Items[count].ITEM_NAME.S)
                    EightLiquorItemArrayList.add(EightliquorItemData(alcoholImg,""+response.Items[count].ITEM_NAME.S,""+response.Items[count].ITEM_TYPE4.S+"원",""+response.Items[count].EVENT_VALUE.S,"#"+response.Items[count].KEYWORD1.S,""+response.Items[count].ITEM_ID.S,""+response.Items[count].KEYWORD2.S,""+response.Items[count].KEYWORD3.S))
                    eigthLiquorAdapter = EightLiquorAdapter(this,EightLiquorItemArrayList)
                    binding.secondRecycler.adapter = eigthLiquorAdapter
                    eigthLiquorAdapter.setItemClickListener(object : EightLiquorAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            val ITEM_ID = EightLiquorItemArrayList.get(position).ITEM_ID
                            // 키워드
                            val keyword1 = EightLiquorItemArrayList.get(position).keyword
                            val keyword2 = EightLiquorItemArrayList.get(position).keyword1
                            val keyword3 = EightLiquorItemArrayList.get(position).keyword2
                            Log.d("아이템아이디",ITEM_ID)
                            val intent = Intent(this@SixActivity, DetailActivity::class.java)
                            intent.putExtra("ITEM_ID",ITEM_ID)
                            intent.putExtra("keyword1",keyword1)
                            intent.putExtra("keyword2",keyword2)
                            intent.putExtra("keyword3",keyword3)
                            startActivity(intent)
                            val postItem = PostItem(ITEM_ID = ITEM_ID)
                            SixItemService(this@SixActivity).tryPostSign(postItem)// 아이디 넘기기.
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