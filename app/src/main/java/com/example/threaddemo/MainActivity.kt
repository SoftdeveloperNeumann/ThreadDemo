package com.example.threaddemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.example.threaddemo.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Runnable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tvBeforeTextView: TextView
    private lateinit var tvAfterTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tvBeforeTextView = binding.tvBefore
        tvAfterTextView = binding.tvAfter

        binding.btnClear.setOnClickListener {
            tvBeforeTextView.text = ""
            tvAfterTextView.text = ""
        }

        binding.btnStart.setOnClickListener {
            aufgabe4()
        }

        binding.btnNext.setOnClickListener {
//            startActivity(Intent(this,TaskActivity::class.java))
            startActivity(Intent(this,CoroutineActivity::class.java))
        }
    }

    private fun aufgabe() {
        tvBeforeTextView.text = "Aufgabe beginnt"
        if (binding.cbSleep.isChecked) {
            Thread.sleep(5000)
        } else {
            while (true);
        }
        tvAfterTextView.text = "Aufgabe fertig"
    }

    private fun aufgabe1() {
        tvBeforeTextView.text = "Aufgabe beginnt"

        Thread(Runnable {

            if (binding.cbSleep.isChecked) {
                Thread.sleep(5000)
            } else {
                while (true);
            }
            tvAfterTextView.text = "Aufgabe fertig"
        }).start()
    }

    private fun aufgabe2() {
        val handler = Handler()

        Thread(Runnable {
            handler.post { tvBeforeTextView.text = "Aufgabe beginnt"}
            if (binding.cbSleep.isChecked) {
                Thread.sleep(5000)
            } else {
                while (true);
            }
            handler.post {tvAfterTextView.text = "Aufgabe fertig"}
        }).start()
    }
    private fun aufgabe3(){
        val thread = Thread(Runnable {
            if (binding.cbSleep.isChecked) {
                Thread.sleep(5000)
            } else {
                while (true);
            }
            runOnUiThread {tvAfterTextView.text = "Aufgabe fertig"}
        })
        tvBeforeTextView.text = "Aufgabe beginnt"
        thread.start()
    }

    private fun aufgabe4(){
        MainScope().launch {
            tvBeforeTextView.text = "Aufgabe mit KotlinFeature"

            withContext(Dispatchers.Default){
                if (binding.cbSleep.isChecked) {
                    //Thread.sleep(5000) // macht man hier anders
                    delay(5000)
                } else {
                    while (true);
                }
            }

            tvAfterTextView.text = "Aufgabe erledigt"
        }
    }

}

