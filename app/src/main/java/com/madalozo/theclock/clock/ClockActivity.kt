package com.madalozo.theclock.clock

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import java.util.*
import android.view.View
import com.madalozo.theclock.R
import java.text.SimpleDateFormat


class ClockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_clock)

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE

        val t: Thread = object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        sleep(1000)
                        runOnUiThread {
                            val textTiming = findViewById<TextView>(R.id.txt_clock)
                            val calendar = Calendar.getInstance()
                            calendar.timeInMillis = System.currentTimeMillis()
                            val updateTimer = String.format("%02d:%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND))
                            textTiming.setText(updateTimer)
                            val tdate = findViewById<View>(R.id.date) as TextView
                            val date = System.currentTimeMillis()
                            val sdf = SimpleDateFormat("MMM dd yyyy")
                            val dateString: String = sdf.format(date)
                            tdate.text = dateString
                        }
                    }
                } catch (e: InterruptedException) {
                }
            }
        }
        t.start()

    }
}