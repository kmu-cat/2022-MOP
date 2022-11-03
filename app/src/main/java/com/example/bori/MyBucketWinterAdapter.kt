package com.example.bori

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyBucketWinterAdapter (val bucketList: ArrayList<BucketListForm>,
                             val onClickHeart: (list: BucketListForm) -> Unit):
    RecyclerView.Adapter<MyBucketWinterAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): MyBucketWinterAdapter.CustomViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_my_bucket_component,parent, false)
        return CustomViewHolder(view)
    }
    override fun getItemCount(): Int{
        return bucketList.size
    }
    override fun onBindViewHolder(holder: MyBucketWinterAdapter.CustomViewHolder, position: Int){
        val listposition = bucketList[position]
        holder.title.text = bucketList.get(position).title.toString()
        holder.challenger.text = bucketList.get(position).challenger.toString()
        holder.heart.setOnClickListener{
            onClickHeart.invoke(listposition)
        }
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.myBucketComponent_titleTextView)
        val challenger = itemView.findViewById<TextView>(R.id.myBucketComponent_challengeTextView)
        val heart = itemView.findViewById<ImageView>(R.id.myBucketComponent_heartImage)
    }




}