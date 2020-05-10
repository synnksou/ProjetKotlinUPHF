package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import kotlinx.android.synthetic.main.content_main.*
import org.w3c.dom.Document

class SigninActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signup);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
        btnSignup.setOnClickListener { this }
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextEmail)
        val btn = findViewById<Button>(R.id.btnSignup)


        btn.setOnClickListener {
            val intent = Intent(this,HomeAcitivity::class.java)
            startActivity(intent);
            finish();
        }

    }

    override fun onStart() {
        super.onStart()


    }


}
