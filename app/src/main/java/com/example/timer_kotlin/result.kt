package com.example.timer_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timer_kotlin.databinding.ActivityResultBinding

class result : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent=intent
        val score=intent.getStringExtra("score")
        binding.scoreId.text= "Score:$score"
    }
}