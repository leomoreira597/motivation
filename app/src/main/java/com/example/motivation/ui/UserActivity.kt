package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.infra.MotivationConsts
import com.example.motivation.R
import com.example.motivation.infra.SecuretyPrefereces
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSalvar.setOnClickListener(this)
        supportActionBar?.hide()

        verificaNomeUsuaro()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_salvar) {
            handleSave()
        }
    }

    private fun verificaNomeUsuaro(){
        val nome = SecuretyPrefereces(this).getString(MotivationConsts.KEY.USER_NAME)
        if (nome != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val nome = binding.editTextNome.text.toString()
        if (nome != "") {
            SecuretyPrefereces(this).storeString(MotivationConsts.KEY.USER_NAME, nome)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation, Toast.LENGTH_SHORT).show()
        }
    }

}