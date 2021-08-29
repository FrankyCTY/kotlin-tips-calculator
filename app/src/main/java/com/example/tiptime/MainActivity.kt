package com.example.tiptime

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {
    // activity_main.xml -> ActivityMainBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val tip = calculateTip()
            binding.tipAmount.text = getString(R.string.tip_amount, tip)
        }
    }

    private fun calculateTip(): String {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull() ?: 0.0

        val selectedRadioBtnId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedRadioBtnId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        val shouldRoundUpTip = binding.roundUpSwitch.isChecked

        // optional: round tip
        if (shouldRoundUpTip) {
            tip = kotlin.math.ceil(tip)
        }

        // Format tip
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        return formattedTip
    }


}