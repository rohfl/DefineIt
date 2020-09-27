package com.rohit.defineit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_one_meaning.*

class OneMeaning : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_meaning)
        val isWord : String? = intent.getStringExtra("word")
        val isSpeech : String? = intent.getStringExtra("speech")
        val isMeaning : String? = intent.getStringExtra("meaning")
        word.text = isWord
        speech.text = isSpeech
        meaning.text = isMeaning
    }
}