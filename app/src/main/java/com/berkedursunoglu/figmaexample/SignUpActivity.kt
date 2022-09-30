package com.berkedursunoglu.figmaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.berkedursunoglu.figmaexample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    ////Variable definition for performing data bindings using the DataBinding library.
    private lateinit var binding : ActivitySignUpBinding

    //Initialization of variables to pass the data entered by the user in the edittext.
    var userName = ""
    var email = ""
    var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ////XML link to binding variable with DataBindingUtil class.
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
    }


    override fun onResume() {
        super.onResume()
        //The code block where the register button is listened to triggered by the user click event.
        binding.registerButton.setOnClickListener {

            //Assigning data in edittex to variables.
            userName = binding.usernameEt.text.toString()
            email = binding.emailAdressEt.text.toString()
            password = binding.passwordEt.text.toString()

            ////Checking if edittexts are empty. If else block to take action according to situations.
            if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password.length >= 8) {
                //A toast message that prints information on the screen if the conditions are met.
                Toast.makeText(this, "Kullanıcı Adı: $userName Email: $email Şifre: $password. Kayıt Başarılı", Toast.LENGTH_LONG).show()
            }else{
                //A toast message that informs the user that the entered data does not match.
                Toast.makeText(this, R.string.toast_info, Toast.LENGTH_LONG).show()
            }
            
             //Scope of actions to be taken when user clicks password hide button.
        binding.ivShowPassword.setOnClickListener {
            //Controlling the state of the view and taking action when the user clicks on the view.
            if (binding.ivShowPassword.id == R.drawable.show_password){
                binding.ivShowPassword.setImageResource(R.drawable.hide_password)
            }else{
                binding.ivShowPassword.setImageResource(R.drawable.show_password)
            }
        }
        }

        //The code block where the request to switch to the previous activity is listened.
        binding.backStackIb.setOnClickListener {
            //The method of switching to the previous activity with the onBackPressed() method when the user clicks the button.
            onBackPressed()
            //The finish() method that executes the termination of the lifecycle of the active activity the user is using.
            finish()
        }

    }
}
