package com.berkedursunoglu.figmaexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.berkedursunoglu.figmaexample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //Variable definition for performing data bindings using the DataBinding library.
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //XML link to binding variable with DataBindingUtil class.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        //The code block where the singupbutton is listened to triggered by the user click event.
        binding.signUpButton.setOnClickListener {
            //Definition of intent to switch to SingUpActivity after trigger.
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        //The code block where the login button is listened to triggered by the user click event.
        binding.loginButton.setOnClickListener {
            //Assigning data in edittext to variables.
            val userName = binding.emailAdressEt.text.toString()
            val userPassword = binding.passwordEt.text.toString()
            //Checking if edittexts are empty.
            if (userName.isNotEmpty() || userPassword.isNotEmpty()) {
                //After triggering, printing the data that the user has written to the edittexts on the screen with a toast message.
                Toast.makeText(this,
                    "Kullanıcı Adı: $userName Şifre: $userPassword Giriş Başarılı.",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}