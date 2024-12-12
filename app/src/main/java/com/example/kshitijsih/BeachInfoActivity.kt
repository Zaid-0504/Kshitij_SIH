package com.example.kshitijsih

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class BeachInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: BeachViewModel
    private val weather_token="d30dc4e0-5f10-11ef-9acf-0242ac130004-d30dc544-5f10-11ef-9acf-0242ac130004"
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beach_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_beach)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button= findViewById<Button>(R.id.button_explore)
        button.setOnClickListener{ v->

            startActivity(Intent(this,ExploreActivity::class.java))

        }

        viewModel = ViewModelProvider(this)[BeachViewModel::class.java]



        val date = LocalDate.now() // Get the current date
        val formatter = DateTimeFormatter.ISO_INSTANT

        val timestamp_start = ZonedDateTime.of(date, LocalTime.MIDNIGHT, ZoneOffset.UTC).toInstant()
        val timestamp_end = ZonedDateTime.of(date, LocalTime.of(23,0,0), ZoneOffset.UTC).toInstant()
        val formattedTimestamp_start = formatter.format(timestamp_start)
        val formattedTimestamp_end = formatter.format(timestamp_end)

        val beachData= BeachData(58.7984,17.8081,formattedTimestamp_start,formattedTimestamp_end,"airTemperature")

        viewModel.loadBeachData(beachData, weather_token).observe(this){data->
            if (data != null) {
                Log.d("TAG_weather", "onCreate: "+ (data.hours?.get(0)?.time ?: "no data"))

            } else {
                Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }


        }

        viewModel.beachData.observe(this){data->
            if (data != null) {
                Log.d("TAG_weather", "onCreate: "+ (data.hours?.get(0)?.time ?: "no data"))

            } else {
                Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }


        }


    }
}