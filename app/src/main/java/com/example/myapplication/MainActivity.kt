package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val btnSignup = findViewById(R.id.btnSignup) as Button;

        Stitch.initializeDefaultAppClient(
            resources.getString(R.string.my_app_id)
        )
        val stitchAppClient = Stitch.getDefaultAppClient()
        stitchAppClient.auth.loginWithCredential(AnonymousCredential())
            .addOnSuccessListener {
                System.out.print("yo bg")
            }

        val mongoClient = stitchAppClient.getServiceClient(
            RemoteMongoClient.factory,
            "mongodb-atlas"
        )

        var myCollection = mongoClient.getDatabase("mongo").getCollection("users");
        val newUser = org.bson.Document();
        var pseudo = "gogolebg"
        newUser["name"] = pseudo;
        newUser["pass"] = 123;

        myCollection.insertOne(newUser)
            .addOnSuccessListener {
                Log.d("STITCH", "One document inserted")
            }

        btnSignup.setOnClickListener {
            val intent=Intent(this,SigninActivity::class.java)
            startActivity(intent);
            finish();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}