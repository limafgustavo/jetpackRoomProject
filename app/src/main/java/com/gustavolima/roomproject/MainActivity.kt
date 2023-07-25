package com.gustavolima.roomproject

import android.content.Intent


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Dao
import com.gustavolima.roomproject.database.AppDatabase
import com.gustavolima.roomproject.database.daos.UserDao
import com.gustavolima.roomproject.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.database = AppDatabase.getInstance(this)
        this.userDao = this.database.UserDao()


    }

    override fun onStart() {
        super.onStart()

        loadTotalUsers()
        openNewUserActivity()
    }

    private fun openNewUserActivity() {
        binding.buttonNewUser.setOnClickListener {
            val intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadTotalUsers() {
        binding.textInfoTotalUsers.text = "Carregando..."
        CoroutineScope(Dispatchers.IO).launch {
            val total = userDao.getTotalItens()
            withContext(Dispatchers.Main){
                binding.textInfoTotalUsers.text = "Total de usuários: $total"
            }

        }

    }
}