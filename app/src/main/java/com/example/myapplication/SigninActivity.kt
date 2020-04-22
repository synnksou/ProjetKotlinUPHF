package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class SigninActivity : AppCompatActivity(){
    var auth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signup);
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextEmail)
        val btn = findViewById<Button>(R.id.btnSignup)
        btn.setOnClickListener {
            signIn(editTextEmail.text.toString(),editTextPassword.text.toString())
        }

    }


    fun signIn( email: String, password: String) {
        //showMessage(view, "Authenticating...")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    var intent = Intent(this, MainActivity::class.java)
                    System.out.println("c ok")
                    intent.putExtra("id", auth.currentUser?.email)
                    startActivity(intent)

                } else {
                    System.out.println("ntm")
                    System.out.print(task.exception?.message);
                   // showMessage(view, "Error: ${task.exception?.message}")
                }
            })

    }
    fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

}