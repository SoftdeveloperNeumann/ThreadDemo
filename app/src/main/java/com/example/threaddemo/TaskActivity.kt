package com.example.threaddemo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.threaddemo.databinding.ActivityMainBinding
import com.example.threaddemo.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    private val werte = arrayOfNulls<String>(20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.btnAction.setOnClickListener {
            MyTask().execute("Frank")
        }

    }

    inner class MyTask: AsyncTask<String,Int,Array<String?>>(){

        lateinit var values :Array<String?>

        override fun onPreExecute() {
          values = werte
        }

        override fun doInBackground(vararg params: String?): Array<String?> {
            for(i in values.indices){
                Thread.sleep(250)
                values[i] = params[0]
                publishProgress(i,values.size)
            }
           return values
        }

        override fun onPostExecute(result: Array<String?>?) {
            for(value in values){
                binding.textView.append(value + "\n")
            }
        }

        override fun onProgressUpdate(vararg values: Int?) {
            Toast.makeText(this@TaskActivity, "${values[0]} von ${values[1]}", Toast.LENGTH_SHORT).show()
        }
    }
}