package com.example.aws.category.first

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aws.databinding.CategoryItemBinding

class FirstAdapter(val context: Context,private val FirstItemArrayList:ArrayList<FirstItemData>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int =FirstItemArrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FirstViewHolder){
            holder.view(FirstItemArrayList[position])
            holder.itemView.setOnClickListener {
                itemClickListener.onClick(it,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(layoutInflater,parent,false)
        return FirstViewHolder(context,binding)
    }
    interface OnItemClickListener{
        fun onClick(v:View,position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private  lateinit var itemClickListener : OnItemClickListener


}