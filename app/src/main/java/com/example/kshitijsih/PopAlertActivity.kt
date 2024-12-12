package com.example.kshitijsih

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PopAlertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pop_alert)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = intent.getStringExtra("title")
        val message = intent.getStringExtra("message")

        Log.d("PopAlertActivity", "Activity started with title: $title, message: $message")

        findViewById<TextView>(R.id.title).text = title
        findViewById<TextView>(R.id.message).text = message

        findViewById<Button>(R.id.send_sos).setOnClickListener {
            Toast.makeText(this, "SOS Sent", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.helpline_numbers).setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:1234567890")))
        }

        findViewById<Button>(R.id.view_safe_zones).setOnClickListener {
            startActivity(Intent(this, Emergency::class.java))
        }
    }
}