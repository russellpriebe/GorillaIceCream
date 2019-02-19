package com.penda.gorillaicecream

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_receipt.*

class ReceiptActivity : AppCompatActivity() {
    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)
        recyclerView2.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView2.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val orders = intent.getParcelableExtra<MenuOrders>("orders")
        val adapter = ReceiptAdapter(context, orders.list)
        recyclerView2.adapter = adapter
        button2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        var sum = 0.0f
        for (item in orders.list){
            val s = item.price.replace("$","")
            val b = s.toFloat()
            sum += b * item.count
        }
        val sumString = "%.2f".format(sum)
        textView6.text = "$$sumString"
    }
}
