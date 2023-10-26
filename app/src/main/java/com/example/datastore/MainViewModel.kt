package com.example.datastore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val pref: SettingPreferences):ViewModel() {
    fun getLoginSettings(): LiveData<Boolean>{
        return pref.getLoginStatus().asLiveData()
    }

    fun saveLoginSetting(loginStatus: Boolean){
        viewModelScope.launch {
            pref.saveLoginStatus(loginStatus)
        }
    }
}