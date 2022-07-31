package com.example.android_aplikacia_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_aplikacia_2022.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.DataOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDb: ApplicationDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = ApplicationDatabase.getDatabase(this)

        binding.btnWriteData.setOnClickListener {

            writeData()
        }

        binding.btnReadData.setOnClickListener {

            readData()
        }
    }

    private  fun writeData(){

        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val rollNo = binding.etRollNo.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && rollNo.isNotEmpty()){

            val user = User(
                null, firstName, lastName, rollNo.toInt()
            )
            GlobalScope.launch(Dispatchers.IO){

                appDb.userDao().insert(user)
            }

            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etRollNo.text.clear()

            Toast.makeText(this@MainActivity, "Uspesne vlozeny User", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this@MainActivity, "Prosim zadajte vsetky potrebne udaje", Toast.LENGTH_SHORT).show()
        }


    }

    private suspend fun displayData(user: User){

        withContext(Dispatchers.Main){

            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvRollNo.text = user.rollNo.toString()
        }
    }

    private  fun readData(){

        val rollNo = binding.etRollNoRead.text.toString()

        if (rollNo.isNotEmpty()){

            lateinit var user: User

            GlobalScope.launch {

                user = appDb.userDao().findByRoll(rollNo.toInt())
                displayData(user)
            }

        }
    }
}