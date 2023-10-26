package com.example.datastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.datastore.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        mainViewModel.getLoginSettings().observe(this) {
            isLogin ->
            if (!isLogin) {
                val intent = Intent(this@MenuActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.btnLogout.setOnClickListener {
            mainViewModel.saveLoginSetting(false).let {
                val intent = Intent(this@MenuActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}