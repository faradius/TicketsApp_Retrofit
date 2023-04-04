package com.developerscracks.ticketsappretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developerscracks.ticketsappretrofit.R
import com.developerscracks.ticketsappretrofit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}