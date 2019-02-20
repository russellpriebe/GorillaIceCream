package com.penda.gorillaicecream

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.content.Context

class SplashActivity : AppCompatActivity() {
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onResume(){
        super.onResume()
        val timer = object: CountDownTimer(2000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                startActivity(Intent(context, MainActivity::class.java))
                finish()}
        }
        timer.start()

    }
}
