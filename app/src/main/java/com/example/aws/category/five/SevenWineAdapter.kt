package com.example.aws.category.five

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.databinding.FifthCategoryItemBinding
import retrofit2.Callback

class SevenWineAdapter(val context: FifthActivity, private val SevenWineItemArrayList: ArrayList<SevenWineItemData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int =SevenWineItemArrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SevenWineViewHolder){
            holder.view(SevenWineItemArrayList[position])
            holder.itemView.setOnClickListener {
                itemClickListener.onClick(it,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FifthCategoryItemBinding.inflate(layoutInflater,parent,false)
        return SevenWineViewHolder(context,binding)
    }
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private  lateinit var itemClickListener : OnItemClickListener




}