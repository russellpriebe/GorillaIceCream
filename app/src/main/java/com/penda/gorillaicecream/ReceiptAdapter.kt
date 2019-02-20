package com.penda.gorillaicecream

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.receipt_item.view.*

class ReceiptAdapter(private val context: Context?, private val cardList: ArrayList<MenuItem>) : androidx.recyclerview.widget.RecyclerView.Adapter<ReceiptAdapter.ViewHolder>(){


    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.receipt_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(cardList[position].count!=2) {
            holder.name.text = cardList[position].getName()
        } else {
            val string = cardList[position].getName()
            holder.name.text = "$string (2)"
        }
        holder.price.text = cardList[position].price

    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val name: TextView = view.textView4
        val price: TextView = view.textView5

    }
}