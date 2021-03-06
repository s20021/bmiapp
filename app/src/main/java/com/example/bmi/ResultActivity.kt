package com.example.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var age_text: TextView = findViewById(R.id.textView1R)
        var index_text: TextView = findViewById(R.id.textView2R)
        var indexresult_text: TextView = findViewById(R.id.textView3L)
        var indexresult:TextView = findViewById(R.id.textView3R)
        var judge_text: TextView = findViewById(R.id.textView4R)
        var weight_text: TextView = findViewById(R.id.textView5R)
        var nomal_weight_text: TextView = findViewById(R.id.textView6R)
        val backbutton: ImageButton = findViewById(R.id.backButton)
        val nw_layout: LinearLayout = findViewById(R.id.view6layout)
        val age_layout: LinearLayout = findViewById(R.id.view1layout)

        val age = intent.getStringExtra("age")?.toIntOrNull()
        val height = intent.getStringExtra("height")?.toDouble() ?: 0.0
        val weight = intent.getStringExtra("weight")?.toDouble() ?: 0.0

        weight_text.setText((Math.floor(weight * 100.0) / 100).toString() + " kg")

        if (age == null) {
            val result = bmiResult(height, weight)
            index_text.setText("ボディマス指数")
            indexresult_text.setText("BMI値")
            indexresult.setText(result.toString())
            judge_text.setText(bmiJudge(result))
            nomal_weight_text.setText(nweightResult(height).toString() + " kg")
            age_layout.visibility = View.GONE
        } else {
            age_text.setText(age.toString())
            if(age >= 18){
                val result = bmiResult(height, weight)
                index_text.setText("ボディマス指数")
                indexresult_text.setText("BMI値")
                indexresult.setText(result.toString())
                judge_text.setText(bmiJudge(result))
                nomal_weight_text.setText(nweightResult(height).toString() + " kg")
            } else if(age >= 6){
                val result = laurelResult(height, weight)
                index_text.setText("ローレル指数")
                indexresult.setText(result.toString())
                judge_text.setText(laurelJudge(result))
                nw_layout.visibility = View.GONE
            } else {
                val result = kaupResult(height, weight)
                index_text.setText("カウプ指数")
                indexresult.setText(result.toString())
                judge_text.setText(kaupJudge(result))
                nw_layout.visibility = View.GONE
            }
        }

        backbutton.setOnClickListener{
            finish()
        }


    }

    fun bmiResult(height : Double, weight : Double) : Double{
        val result = weight / Math.pow(height * 0.01, 2.0)
        return (Math.floor(result * 100.0) / 100.0)
    }
    fun laurelResult(height : Double, weight : Double) : Double{
        val result = weight / Math.pow(height * 0.01, 3.0) * 10.0
        return (Math.floor(result * 100.0) / 100.0)
    }
    fun kaupResult(height : Double, weight : Double) : Double{
        val result = weight / Math.pow(height * 0.01, 2.0)
        return (Math.floor(result * 100.0) / 100.0)
    }

    fun nweightResult(height: Double) : Double {
        val result = Math.pow(height * 0.01, 2.0) * 22.0
        return (Math.floor(result * 10.0) / 10.0)
    }

    fun bmiJudge(bmiindex : Double) : String{
        if (bmiindex < 18.5) {
            return "低体重"
        } else if (bmiindex < 25.0) {
            return "標準体重"
        } else if (bmiindex < 30.0) {
            return "肥満(1度)"
        } else if (bmiindex < 35.0) {
            return "肥満(2度)"
        } else if (bmiindex < 40.0) {
            return "肥満(3度)"
        } else {
            return "肥満(4度)"
        }
    }
    fun laurelJudge(lauralindex : Double) : String{
        if (lauralindex < 100.0) {
            return "やせすぎ"
        } else if (lauralindex < 115.0) {
            return "やせてる"
        } else if (lauralindex < 145.0) {
            return "ふつう"
        } else if (lauralindex < 160.0) {
            return "ふとっている"
        } else {
            return "ふとりすぎ"
        }
    }
    fun kaupJudge(kaupindex : Double) : String{
        if (kaupindex <= 14) {
            return "やせぎみ"
        } else if (kaupindex <= 17) {
            return "ふつう"
        } else {
            return "ふとりぎみ"
        }
    }
}