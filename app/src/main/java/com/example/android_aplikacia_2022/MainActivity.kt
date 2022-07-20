package com.example.android_aplikacia_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_aplikacia_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDb: ApplicationDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWriteData.setOnClickListener {

            writeData()
        }

        binding.btnReadData.setOnClickListener {

            readData()
        }
    }

    private  fun writeData(){


    }

    private  fun readData(){


    }
}