package com.madalozo.theclock.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.madalozo.theclock.R
import android.view.View
import android.widget.Button


class TimerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_timer)
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            progress()
        }
    }
    private fun progress() {
        val seekBar = findViewById<SeekBar>(R.id.progressBar)
        seekBar.max = (durationSeekBar()?.times(60000))?.toInt()!!
        val countDownTimer =
            object : CountDownTimer(durationSeekBar()?.times(60000)?.toLong()!!, 1) {
                override fun onTick(millisUntilFinished: Long) {
                        val minute = (millisUntilFinished / 1000) / 60
                        var seconds = (millisUntilFinished / 1000) % 60
                    seekBar.progress =
                            (durationSeekBar()?.times(60000)?.minus(millisUntilFinished))?.toInt()!!
                    val tv = findViewById<View>(R.id.txt_stopwatch) as TextView
                    tv.text = "$minute:$seconds"
                    seconds -= 1

                }

                override fun onFinish() {
                }
            }
        countDownTimer.start()
    }

    fun durationSeekBar(): Double? {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        return sharedPref.getString("stopwatch_minutes", "3")?.toDouble()
    }

}