package com.example.firstshit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.system.exitProcess

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val mes = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.textView).apply {
            text = mes

        }
        val notifyIntent = Intent()
        setResult(1, notifyIntent)
    }

    fun send2Storage(view: View) {
        val queue = Volley.newRequestQueue(this)
        val text = intent.getStringExtra(EXTRA_MESSAGE)
        val params = HashMap<String,String>()
        params["message"] = text
        val jsonObject = JSONObject(params as Map<*, *>)
        val url = "http://192.168.0.104:9999/save"
        println(jsonObject)
        val request =
            JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                Response.Listener<JSONObject> {response ->
                    println("my resp =$response")
                },
                Response.ErrorListener {error ->
                    println("error is $error")
                })
        queue.add(request)
    }

}
