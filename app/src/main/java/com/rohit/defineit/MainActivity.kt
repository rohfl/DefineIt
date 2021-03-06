package com.rohit.defineit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.JsonArrayRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var isword : String? = null
    private var url : String? = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    private val wrongWord : String = "No Definitions Found"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defineit.setOnClickListener {
            // Do some work here
            isword = isWord.text.toString()
            if(isword!=null){
                url += isword
                getMeaning()
                url = "https://api.dictionaryapi.dev/api/v2/entries/en/"
            }
            else {
                Toast.makeText(this, "Enter a Word !", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun getMeaning() {
        val jsonArrayRequest = JsonArrayRequest(GET,
            url,
            null,
            { response ->
                var obj: JSONObject? = response.getJSONObject(0)
                val jsonArray: JSONArray? = obj?.getJSONArray("meanings")
                val obj2: JSONObject? = jsonArray?.getJSONObject(0)
                val partOfSpeech: String = obj2?.getString("partOfSpeech") ?: String()
                val definitions: JSONArray? = obj2?.getJSONArray("definitions")
                val obj3: JSONObject? = definitions?.getJSONObject(0)
                val meaning: String = obj3?.getString("definition") ?: String()

                val intent: Intent = Intent(this, OneMeaning::class.java).apply {
                    putExtra("word", isword)
                    putExtra("speech",partOfSpeech)
                    putExtra("meaning",meaning)
                }
                startActivity(intent)


            },
            {
                Toast.makeText(this, "Something is Wrong !", Toast.LENGTH_SHORT).show()
            }
        )
        MySingleTon.getInstance(this)?.addToRequestQue(jsonArrayRequest)
    }
}