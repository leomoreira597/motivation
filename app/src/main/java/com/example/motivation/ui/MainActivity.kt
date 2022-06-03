package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConsts
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecuretyPrefereces
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConsts.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        handleUserName()
        handleFilter(R.id.image_all)
        handleNextPhrase()

        binding.btnNovaFrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_nova_frase){
            handleNextPhrase()
        }
        else if(view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)){
            handleFilter(view.id)
        }

    }

    private fun handleNextPhrase(){
        val phrase = Mock().getPhrase(categoryId)
        binding.textMotivation.text = phrase
    }

    private fun handleFilter(id: Int){
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.darkpurple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.darkpurple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.darkpurple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConsts.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConsts.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConsts.FILTER.SUNNY
            }
        }
    }

    private fun handleUserName(){
        val nome = SecuretyPrefereces(this).getString(MotivationConsts.KEY.USER_NAME)
        binding.textUserNome.text = "Olá, $nome!"
    }
}