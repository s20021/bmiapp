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
            val age_check = ageInputCheck(age_num)

            if(age_num.text.length == 0 || age_check) {
                if(hwInputCheck(height_num) && hwInputCheck(weight_num)) {
                    val nextIntent = Intent(this, ResultActivity::class.java)
                    nextIntent.putExtra("age", age_num.text.toString())
                    nextIntent.putExtra("height", height_num.text.toString())
                    nextIntent.putExtra("weight", weight_num.text.toString())
                    startActivity(nextIntent)
                }
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

        val int_hw = hw.text.toString().toIntOrNull()

        if(int_hw == null){
            hw.requestFocus()
            Toast.makeText(applicationContext, "整数で値を入力してください", Toast.LENGTH_SHORT).show()
            return false
        }

        if(int_hw<= 0 || int_hw > 300){
            hw.requestFocus()
            Toast.makeText(applicationContext, "正しい値を入力してください", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun ageInputCheck(age : EditText) : Boolean {
        if(age.text.length == 0) {return true}

        val int_age = age.text.toString().toIntOrNull()

        if(int_age == null) {
            age.requestFocus()
            Toast.makeText(applicationContext, "整数で値を入力してください", Toast.LENGTH_SHORT).show()
            return false
        }

        if(int_age < 0 || int_age > 200) {
            age.requestFocus()
            Toast.makeText(applicationContext, "正しい値を入力してください", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}

