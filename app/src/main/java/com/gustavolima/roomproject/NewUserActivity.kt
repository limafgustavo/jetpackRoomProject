package com.gustavolima.roomproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gustavolima.roomproject.database.AppDatabase
import com.gustavolima.roomproject.database.daos.UserDao
import com.gustavolima.roomproject.database.models.User
import com.gustavolima.roomproject.databinding.ActivityNewUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewUserBinding
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)
        userDao = database.UserDao()
    }

    override fun onStart() {
        super.onStart()

        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = saveUser(
                    binding.editFirstName.text.toString(),
                    binding.editLastName.text.toString()
                )
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@NewUserActivity,
                        if (result) "User saved!" else "Error trying to save the user",
                        Toast.LENGTH_SHORT
                    ).show()
                    if (result) {

                            withContext(Dispatchers.Main){
                                startActivity()
                            }

                        finish()
                    }
                }
            }
        }

    }

    private fun startActivity(){
        startActivity(Intent(this, MainActivity::class.java))
    }
    private suspend fun saveUser(firstName: String, lastName: String): Boolean {
        if (firstName.isBlank() || firstName.isEmpty()) {
            return false
        }
        if (lastName.isBlank() || lastName.isEmpty()) {
            return false
        }
        userDao.insert(User(firstName, lastName))
        return true
    }
}