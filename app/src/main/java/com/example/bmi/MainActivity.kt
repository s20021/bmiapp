package com.example.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var age_num:EditText = findViewById(R.id.age_input)
        var height_num:EditText = findViewById(R.id.height_input)
        var weight_num:EditText = findViewById(R.id.weight_input)
        val calc_btn:Button = findViewById(R.id.calc_button)
        val clear_btn:Button = findViewById(R.id.clear_button)



        calc_btn.setOnClickListener {
            val height_check = hwInputCheck(height_num)
            val weight_check = hwInputCheck(weight_num)

            if(height_check && weight_check){
                val nextIntent = Intent(this, ResultActivity::class.java)
                startActivity(nextIntent)
            }
        }

        clear_btn.setOnClickListener {
            age_num.text.clear()
            height_num.text.clear()
            weight_num.text.clear()
        }
    }

    fun hwInputCheck(hw : EditText) : Boolean {
        if(hw.text.toString().length == 0){
            hw.requestFocus()
            Toast.makeText(applicationContext,"値を入力してください", Toast.LENGTH_SHORT).show()
            return false
        }

        val int_height = hw.text.toString().toIntOrNull() ?:0

        if(int_height<= 0 || int_height > 300){
            hw.requestFocus()
            Toast.makeText(applicationContext, "正しい値を入力してください", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}

