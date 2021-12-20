package com.example.aws.category.five

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.databinding.FifthCategoryItemBinding
import retrofit2.Callback

class FourthWineAdapter(val context: FifthActivity, private val FourthWineItemArrayList: ArrayList<FourthWineItemData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int =FourthWineItemArrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FourthWineViewHolder){
            holder.view(FourthWineItemArrayList[position])
            holder.itemView.setOnClickListener {
                itemClickListener.onClick(it,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FifthCategoryItemBinding.inflate(layoutInflater,parent,false)
        return FourthWineViewHolder(context,binding)
    }

    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private  lateinit var itemClickListener : OnItemClickListener



}