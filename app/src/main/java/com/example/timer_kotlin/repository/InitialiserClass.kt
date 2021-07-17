package com.example.timer_kotlin.repository

import com.example.timer_kotlin.database.Questions
import com.example.timer_kotlin.model.QandA

object InitialiserClass {

    fun setData(){
       Questions.questionsData.add(
           QandA("What is kotlin","Programming language","animal","bird","tree","a"))
        Questions.questionsData.add(
            QandA("What is java","Programming language","animal","bird","tree","a"))
        Questions.questionsData.add(
            QandA("What is python","Programming language","animal","bird","tree","a"))
        Questions.questionsData.add(
            QandA("What is C++","Programming language","animal","bird","tree","a"))
    }
}