@file:Suppress("DEPRECATION")

package com.example.bmicalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //이전에 입력한 값을 읽어오기
        loadData()

        resultButton.setOnClickListener {
            //결과 버튼이 클릭되면 할 일을 작성하는 부분
            /*
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("weight",weightEditText.text.toString())
            intent.putExtra("height",weightEditText.text.toString())

            //startActivity(intent)
            //use anko
            */
            saveData(heightEditText.text.toString().toInt(),
                    weightEditText.text.toString().toInt())

            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()

            )

        }
    }

    private fun saveData(height : Int, weight : Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT",height)
            .putInt("KEY_WEIGHT",weight)
            .apply()
    }
    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT",0)
        val weight = pref.getInt("KEY_WEIGHT",0)

        if(height != 0 && weight != 0){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }

}