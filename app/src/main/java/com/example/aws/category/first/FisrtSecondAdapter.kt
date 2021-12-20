package com.example.aws.category.first

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aws.databinding.CatecoryFirstItemBinding
import com.example.aws.databinding.CategoryItemBinding


class FirstSecondAdapter(val context: Context, private val FirstSecondItemArrayList:ArrayList<FirstSecondItemData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int =FirstSecondItemArrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FirstSecondViewHolder){
            holder.view(FirstSecondItemArrayList[position])
            holder.itemView.setOnClickListener {
                itemClickListener.onClick(it,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatecoryFirstItemBinding.inflate(layoutInflater,parent,false)
        return FirstSecondViewHolder(context,binding)
    }
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private  lateinit var itemClickListener : OnItemClickListener



}