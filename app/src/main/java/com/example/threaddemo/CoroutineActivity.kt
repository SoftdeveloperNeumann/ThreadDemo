package com.example.threaddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.threaddemo.databinding.ActivityTaskBinding
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    private val werte = arrayOfNulls<String>(20)
    // Initialisieren der verwendeten Scopes
    private val mainScope = Dispatchers.Main
    private val defaultScope = Dispatchers.Default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAction.setOnClickListener {
            CoroutineScope(defaultScope).launch {
                doInBackground("Frank")
            }
        }
    }

    suspend fun doInBackground(vararg params:String){
        withContext(mainScope) { binding.progressBar.visibility = View.VISIBLE }

        for (i in werte.indices){
            withContext(mainScope){
                publishProgress(i)
            }
            delay(250)
            werte[i] = params[0]

            withContext(mainScope){
                binding.textView.append(werte[i] + "\n")
            }
        }
        withContext(mainScope){
            binding.progressBar.progress = 0
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
    fun publishProgress(vararg values:Int){
        binding.progressBar.progress = values[0]
    }
}