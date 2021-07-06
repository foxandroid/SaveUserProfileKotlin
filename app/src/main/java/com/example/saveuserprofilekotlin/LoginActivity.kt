package com.example.saveuserprofilekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.saveuserprofilekotlin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loginBtn.setOnClickListener {

            val etLogin = binding.etEmail.text.toString()
            val etPassword = binding.etPassword.text.toString()

            auth.signInWithEmailAndPassword(etLogin,etPassword)
                .addOnCompleteListener(this){

                    if (it.isSuccessful){

                        val i = Intent(this@LoginActivity,MainActivity::class.java)
                        startActivity(i)

                    }else{

                        Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()

                    }

                }

        }


    }

    override fun onStart() {

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val i = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(i)
        }
        super.onStart()
    }

}