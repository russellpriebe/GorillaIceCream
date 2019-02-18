package com.penda.gorillaicecream

import android.content.Context
import android.graphics.drawable.Drawable

data class MenuItem(val name1: String, val name2: String, val price: String, val bg_color: String, val type: String) {
    fun getColorInteger(): Int {
        return bg_color.toInt()
    }
    fun getMenuItemImage(context: Context?): Drawable?{
        var drawable: Drawable? = null
        context?.let {
            when {
                type.equals("popsicle") -> drawable = context.resources.getDrawable(R.drawable.popsicle)
                type.equals("cone") -> drawable = context.resources.getDrawable(R.drawable.cone)
                type.equals("froyo") -> drawable = context.resources.getDrawable(R.drawable.froyo)
                type.equals("sundae") -> drawable = context.resources.getDrawable(R.drawable.ice_cream)
            }
        }
        return drawable
    }

    fun getName() : String {
        return "$name1 $name2"
    }
}