package com.example.timer_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timer_kotlin.databinding.ActivityLauncherBinding

class LauncherActivity : AppCompatActivity() {
    lateinit var binding:ActivityLauncherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityLauncherBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        handleStartButtonClick()
    }

    private fun handleStartButtonClick() {
        binding.startButtonId.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}