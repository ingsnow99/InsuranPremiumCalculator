package com.example.insuranpremiumcalculator

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData:PremiumModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)
        val calculatebtn=findViewById<Button>(R.id.cal_btn_id)
        val resetbtn=findViewById<Button>(R.id.reset_btn_id)

        calculatebtn.setOnClickListener()
        {
            myData.total_premium="RM%.2f".format(getPremium())
            display()
        }
        resetbtn.setOnClickListener {
            age_spinner.selectedItemPosition
            age_spinner.setSelection(0)
            gender_group_id.clearCheck()
            check_smoker_id.setChecked(false)
            premium_result_id.text="RM0.00"
        }
            display()
    }

    fun display()
    {
        premium_result_id.text=myData.total_premium
    }
    fun getPremium():Double
    {
        return when(age_spinner.selectedItemPosition)
        {
            0->60.00
            1->70.00+
            (if(gender_male_id.isChecked) 50.00 else 0.0)+
                    (if(check_smoker_id.isChecked) 100.00 else 0.0)
            2->50.00+
                    (if(gender_female_id.isChecked)100.00 else 0.0)+
                    (if(check_smoker_id.isChecked)150.00 else 0.0)

            3->120.00
            4->150.00
            else->150.00
        }

    }
}
