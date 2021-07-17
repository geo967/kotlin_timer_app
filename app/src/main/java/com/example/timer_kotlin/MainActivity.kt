package com.example.timer_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.timer_kotlin.database.Questions
import com.example.timer_kotlin.databinding.ActivityMainBinding
import com.example.timer_kotlin.model.QandA
import com.example.timer_kotlin.repository.InitialiserClass
import com.example.timer_kotlin.viewmodel.MainActivityVM

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
   // lateinit var questionsData: List<QandA>
    lateinit var timer: CountDownTimer
    lateinit var intent1:Intent

    //view model object
    lateinit var vm:MainActivityVM
    //  private lateinit var timer: CountDownTimer
    var totalQn: Int = 0
    var l = mutableListOf<String>()
    //var answer = mutableListOf<String>()
    var score = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //vm initialization
        vm=ViewModelProvider(this)[MainActivityVM::class.java]

        //timer observer
        vm.timerObserve.observe(this, Observer {
            binding.countTime.text=it.toString()
        })
        //qn left observer
        vm.totalQuestionsLeft.observe(this, Observer {
            binding.qnLeftId.text=it.toString()
        })
        vm.questionId.observe(this, Observer {
            binding.questionId.text=it.toString()
        })
        vm.radioButton1.observe(this, Observer {
            binding.radioButton1.text=it.toString()
        })
        vm.radioButton2.observe(this, Observer {
            binding.radioButton2.text=it.toString()
        })
        vm.radioButton3.observe(this, Observer {
            binding.radioButton3.text=it.toString()
        })
        vm.radioButton4.observe(this, Observer {
            binding.radioButton4.text=it.toString()
        })

        intent1 = Intent(this, result::class.java)
        var i = 0

        if(vm.totalQn==0) {
            resetTimer()
            InitialiserClass.setData()
            vm.setQn(binding)
            /*questionsData = Questions.questionsData
        totalQn = questionsData.size - 1
        binding.qnLeftId.text = "Qn Left: $totalQn"
        for (element in questionsData) {
            answer.add(element.correct)
        }*/
            vm.questionId.value = vm.questionsData[0].q
            vm.radioButton1.value = vm.questionsData[0].a1
            vm.radioButton2.value = vm.questionsData[0].a2
            vm.radioButton3.value = vm.questionsData[0].a3
            vm.radioButton4.value = vm.questionsData[0].a4
            binding.questionId.text = vm.questionsData[0].q
            binding.radioButton1.text = vm.questionsData[0].a1
            binding.radioButton2.text = vm.questionsData[0].a2
            binding.radioButton3.text = vm.questionsData[0].a3
            binding.radioButton4.text = vm.questionsData[0].a4
             i = 1
        }
        binding.nextButtonId.setOnClickListener {
            binding.radioGpId.clearCheck()
            val x=(--vm.totalQn)
            binding.qnLeftId.text = "Qn left:$x"
            vm.totalQuestionsLeft.value=x
            if (i < vm.questionsData.size) {
                vm.questionId.value = vm.questionsData[i].q
                vm.radioButton1.value  = vm.questionsData[i].a1
                vm.radioButton2.value  = vm.questionsData[i].a2
                vm.radioButton3.value  = vm.questionsData[i].a3
                vm.radioButton4.value  = vm.questionsData[i].a4
                binding.questionId.text = vm.questionsData[i].q
                binding.radioButton1.text = vm.questionsData[i].a1
                binding.radioButton2.text = vm.questionsData[i].a2
                binding.radioButton3.text= vm.questionsData[i].a3
                binding.radioButton4.text =vm. questionsData[i].a4
                i++
            }
            if (i == vm.questionsData.size) {
                binding.nextButtonId.visibility = View.GONE
            }
            timer.cancel()
            resetTimer()
        }
    }

     private fun resetTimer() {
         vm.counter = 10
         timer = object : CountDownTimer(10000, 1000) {
             override fun onTick(millisUntilFinished: Long) {
                 binding.countTime.text = (vm.counter).toString()
                 vm.counter--
                 vm.timerObserve.value=vm.counter
                 binding.radioGpId.setOnCheckedChangeListener { _, checkedId ->
                     if (checkedId == R.id.radio_button_1) {
                         l.add("a")
                         if (binding.nextButtonId.visibility == View.VISIBLE) {
                             binding.nextButtonId.performClick()
                         }
                     }
                     if (checkedId == R.id.radio_button_2) {
                         l.add("b")
                         if (binding.nextButtonId.visibility == View.VISIBLE) {
                             binding.nextButtonId.performClick()
                         }
                     }
                     if (checkedId == R.id.radio_button_3) {
                         l.add("c")
                         if (binding.nextButtonId.visibility == View.VISIBLE) {
                             binding.nextButtonId.performClick()
                         }
                     }
                     if (checkedId == R.id.radio_button_4) {
                         l.add("d")
                         if (binding.nextButtonId.visibility == View.VISIBLE) {
                             binding.nextButtonId.performClick()
                         }
                     }
                 }
             }

             @SuppressLint("SetTextI18n")
             override fun onFinish() {
                 binding.countTime.text = "finished"
                 val intSelectButton: Int = binding.radioGpId.checkedRadioButtonId
                 if (intSelectButton == -1) {
                     l.add("empty")
                 }
                 if (vm.totalQn > 0) {
                     binding.nextButtonId.performClick()
                 } else {
                     for (j in 0 until l.size) {
                         if (l[j] == vm.answer[j]) {
                             score++
                         }
                     }
                     intent1.putExtra("score", score.toString())
                     startActivity(intent1)
                 }
             }
         }
         timer.start()
     }

}