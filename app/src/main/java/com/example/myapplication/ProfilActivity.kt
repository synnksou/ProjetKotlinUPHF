package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R.id.textViewPrenom
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("UNREACHABLE_CODE")
class ProfilActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference


    companion object {
        val TAG = "RegisterActivity"
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.profil_layout)
        setSupportActionBar(toolbar)
// ...
        database = Firebase.database.reference
        var myuser = FirebaseAuth.getInstance().currentUser

        val textViewName = findViewById<EditText>(R.id.textViewPrenom) as EditText
        val textViewEmail = findViewById<EditText>(R.id.textViewEmail) as EditText

        val uid = FirebaseAuth.getInstance().uid


    }


}