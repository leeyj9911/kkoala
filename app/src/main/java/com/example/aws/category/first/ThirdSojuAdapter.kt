package com.example.aws.category.first

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aws.databinding.CategoryItemBinding
import com.example.aws.databinding.CategorySojuThirdItemBinding

class ThirdSojuAdapter(val context: Context, private val ThirdSojuItemArrayList:ArrayList<ThirdSojuItemData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int =ThirdSojuItemArrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ThirdSojuViewHolder){
            holder.view(ThirdSojuItemArrayList[position])
            holder.itemView.setOnClickListener {
                itemClickListener.onClick(it,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategorySojuThirdItemBinding.inflate(layoutInflater,parent,false)
        return ThirdSojuViewHolder(context,binding)
    }
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private  lateinit var itemClickListener : OnItemClickListener




}