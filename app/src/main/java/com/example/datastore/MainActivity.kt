package com.example.datastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.datastore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]
        mainViewModel.getLoginSettings().observe(this) { isLogin ->
            if (isLogin) {
                val intent = Intent(this@MainActivity, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        binding.btnLogin.setOnClickListener {
            mainViewModel.saveLoginSetting(true).let {
                val intent =   Intent(this@MainActivity,MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}