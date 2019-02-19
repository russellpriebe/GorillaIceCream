package com.penda.gorillaicecream

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuOrders(val list: ArrayList<MenuItem>): Parcelable {
}