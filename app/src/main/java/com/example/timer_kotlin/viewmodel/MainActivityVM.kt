package com.example.timer_kotlin.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timer_kotlin.R
import com.example.timer_kotlin.database.Questions
import com.example.timer_kotlin.databinding.ActivityMainBinding
import com.example.timer_kotlin.model.QandA

class MainActivityVM:ViewModel() {

    var counter = 0
    lateinit var questionsData: List<QandA>
    var totalQn: Int = 0
    var l = mutableListOf<String>()
    var answer = mutableListOf<String>()
    var score = 0

    //live data
    val timerObserve: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val totalQuestionsLeft: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val questionId: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val radioButton1: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val radioButton2: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val radioButton3: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val radioButton4: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    //connecting to db
    @SuppressLint("SetTextI18n")
    fun setQn(binding:ActivityMainBinding) {
        questionsData = Questions.questionsData
        totalQn = questionsData.size - 1
        binding.qnLeftId.text = "Qn Left: $totalQn"
        for (element in questionsData) {
            answer.add(element.correct)
        }
    }

    //connecting vm and view

}