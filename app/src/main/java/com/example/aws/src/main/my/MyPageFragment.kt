package com.example.aws.src.main.my

import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.bumptech.glide.Glide
import com.example.aws.R
import com.example.aws.config.BaseFragment
import com.example.aws.databinding.FragmentMyPageBinding
import com.example.aws.src.login.AuthActivity
import com.example.aws.src.main.MainActivity
import okhttp3.internal.threadName
import java.lang.Exception
import java.util.*


open class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    private val OPEN_GALLERY = 0
    private var imageView: ImageView? = null
    //lateinit var userName : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로필 이미지 변경
        binding.profileimgView.setOnClickListener() {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, OPEN_GALLERY)
        }

        binding.btnWriteReview.setOnClickListener(){
            (activity as MainActivity).changeFragment(WriteReviewFragment())
        }

        fun getUserInfo() {
            AWSMobileClient.getInstance().getUserAttributes(object : Callback<Map<String, String>> {
                @SuppressLint("SetTextI18n")
                override fun onResult(result: Map<String, String>?) {
                    result?.get("name").toString()
                    println("%%%%%%%%%%%%%%%%%"+result?.get("name").toString())
                    println("bbbbbbbbbbb"+result?.get("birthdate").toString())
                }
                override fun onError(e: java.lang.Exception?) {
                    println(e)
                }
            })
        }









        binding.btnLogout.setOnClickListener {
            AWSMobileClient.getInstance().initialize(
                requireContext(),
                object : Callback<UserStateDetails?> {
                    override fun onResult(userStateDetails: UserStateDetails?) {
                        // 로그아웃 후 로그인 창으로 이동
                        AWSMobileClient.getInstance().signOut()
                        val i = Intent(activity, AuthActivity::class.java)
                        startActivity(i)
                    }

                    override fun onError(e: Exception) {}
                })
        }

        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == OPEN_GALLERY){
            if(resultCode == RESULT_OK){
                try{
                    var uri: Uri? = data?.data
                    Glide.with(requireContext()).load(uri).into(binding.profileimgView)
                } catch (e:Exception){
                    //
                }
            }
            else if(resultCode == RESULT_CANCELED){
                //Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
            }
        }
    }

}