package com.penda.gorillaicecream

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: GorillaViewModel
    val context: Context = this
    var menuItems: ArrayList<MenuItem> = ArrayList<MenuItem>()
    var orderedItems: ArrayList<MenuItem> = ArrayList<MenuItem>()
    var noOfItemsOrdered = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }
    private fun setupViewModel(){
        viewModel = ViewModelProviders.of(this).get(GorillaViewModel::class.java)
        viewModel.fetchMenu("http://gl-endpoint.herokuapp.com/products")
        viewModel.returnBundle.observe(this, Observer {
            it?.let { items ->
                menuItems = items
                setupGridView()
            }?:run{
                Toast.makeText(context, "No Menu Items Found!", Toast.LENGTH_LONG).show()
            }

        })
        viewModel.errorBundle.observe(this, Observer {

        })
    }
    private fun setupGridView(){
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
        val adapter = CardListAdapter(context, menuItems)
        recyclerView.adapter = adapter
        adapter.cardClicked.observe(this, Observer { item ->
            if(item.count==0){
                orderedItems.add(item)
            }
            item.count++
            noOfItemsOrdered++
            if(item.count==3){
                orderedItems.remove(item)
                noOfItemsOrdered -= 2
            }
            if(noOfItemsOrdered==1) {
                button.text = "Order 1 Item"
            } else {
                button.text = "Order $noOfItemsOrdered Items"
            }

        })


    }

}
