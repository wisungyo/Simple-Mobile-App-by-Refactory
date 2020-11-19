package com.wisungyo.simplemobileapp_refactory

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wisungyo.simplemobileapp_refactory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var doubleClick = 0
        var historyCounter = 0
        var inputBank = arrayListOf<String>()

        binding.btnReverse.setOnClickListener {
            binding.tvOutput.text = binding.etInput.text.reversed()
            inputBank.add(binding.etInput.text.toString())
            historyCounter = inputBank.size-1
        }

        binding.btnUndoRedo.setOnClickListener {
            doubleClick++
            val handler = Handler()
            val r = Runnable { doubleClick = 0 }

            if (doubleClick == 1) {
                //Single click
                handler.postDelayed(r, 250)
                if (historyCounter > 0) {
                    historyCounter--
                    binding.etInput.setText(inputBank[historyCounter])
                } else {
                    binding.etInput.setText(inputBank[0])
                }
            } else if (doubleClick == 2) {
                //Double click
                doubleClick = 0
                if (historyCounter < inputBank.size-2) {
                    historyCounter+=2
                    binding.etInput.setText(inputBank[historyCounter])
                } else {
                    binding.etInput.setText(inputBank[inputBank.size-1])
                }
            }
        }
    }
}