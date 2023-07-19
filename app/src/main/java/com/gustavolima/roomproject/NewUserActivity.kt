package com.gustavolima.roomproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gustavolima.roomproject.database.AppDatabase
import com.gustavolima.roomproject.database.daos.UserDao
import com.gustavolima.roomproject.databinding.ActivityNewUserBinding

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
}