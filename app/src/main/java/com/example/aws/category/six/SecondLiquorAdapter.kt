package com.example.aws.category.six

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aws.databinding.SixCategoryItemBinding

class SecondLiquorAdapter(val context: SixActivity, private val SecondLiquorArrayList: ArrayList<SecondliquorItemData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int =SecondLiquorArrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SecondLiquorViewHolder){
            holder.view(SecondLiquorArrayList[position])
            holder.itemView.setOnClickListener {
                itemClickListener.onClick(it,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SixCategoryItemBinding.inflate(layoutInflater,parent,false)
        return SecondLiquorViewHolder(context,binding)
    }
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private  lateinit var itemClickListener : OnItemClickListener



}