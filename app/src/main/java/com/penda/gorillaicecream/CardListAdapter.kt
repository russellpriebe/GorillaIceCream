package com.penda.gorillaicecream

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.grid_item.view.*

class CardListAdapter(private val context: Context?, private val cardList: ArrayList<MenuItem>) : androidx.recyclerview.widget.RecyclerView.Adapter<CardListAdapter.ViewHolder>(){

    var cardClicked = MutableLiveData<MenuItem>()

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = cardList[position].getName()
        holder.price.text = cardList[position].price
        holder.image.setImageDrawable(cardList[position].getMenuItemImage(context))
        val drawable = context?.resources?.getDrawable(R.drawable.circle) as GradientDrawable
        val colorInt = cardList[position].getColorInteger()
        val colorString = cardList[position].bg_color
        Log.d("colorint", "$colorInt $colorString")
       // val d = drawable as GradientDrawable
        drawable.setColor(colorInt)
        holder.card.setOnClickListener {
            cardClicked.postValue(cardList[position])
        }

    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val name: TextView = view.textView
        val card: ConstraintLayout = view.card
        val price: TextView = view.textView2
        val image: ImageView = view.imageView

    }
}
