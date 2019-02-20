package com.penda.gorillaicecream

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONArray

class GorillaViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    var returnBundle: MutableLiveData<java.util.ArrayList<MenuItem>> = MutableLiveData()
    var errorBundle: MutableLiveData<String> = MutableLiveData()

    fun fetchMenu(url: String){
        uiScope.launch(Dispatchers.IO){
            Utilities.httpGET(url)?.let{
                when {
                    it.containsKey("valid") -> { val data: String? = it.getString("valid")
                        data?.let{
                            parseMenu(data)
                        }
                    }
                    it.containsKey("malformedURL") -> { val data: String? = it.getString("malformedURL")
                        data?.let {
                        }
                    }
                    it.containsKey("io") -> {
                        val data: String? = it.getString("io")
                        data?.let {
                        }
                    }
                    else -> { }
                }
            }?:run{
            }

        }
    }

    fun parseMenu(json: String) {

        val menuItems: ArrayList<MenuItem> = ArrayList<MenuItem>()
        if(json.isEmpty()){
            errorBundle.postValue("data error")
        }
        try {
            val list = JSONArray(json)

            for (i in 0 until list.length()) {
                val item = list.getJSONObject(i)
                val name1 = item.getString("name1")
                val name2 = item.getString("name2")
                val price = item.getString("price")
                val bgColor = item.getString("bg_color")
                val mType = item.getString("type")
                menuItems.add(MenuItem(name1, name2, price, bgColor, mType))
            }
        } catch (e: Exception){
            errorBundle.postValue("data error")
        }
        returnBundle.postValue(menuItems)
    }
}