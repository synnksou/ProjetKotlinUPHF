package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.layout_signup.*


class HomeAcitivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    public override fun onStart() {
        super.onStart()
        //val currentUser = auth.currentUser

        // Check if user is signed in (non-null) and update UI accordingly.
    }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)
        val editTitle = findViewById<EditText>(R.id.editTextTextPersonName)
        val editDescription = findViewById<EditText>(R.id.editTextTextMultiLine)
        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            onClickButton(editTitle,editDescription)
        }

        verifyUserIsLoggedIn()

       val bottomNavigationView = findViewById(R.id.bottomNav) as BottomNavigationView


        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.bottomNavigationsHome -> {
                    val intent = Intent(this,HomeAcitivity::class.java)
                    startActivity(intent);
                    finish();
                   true
                }
                R.id.bottomNavigationsSearch -> {
                    val intent = Intent(this,PostsListActivity::class.java)
                    startActivity(intent);
                    finish();
                    true
                }
                R.id.bottomNavigationProfil -> {
                    val toast = Toast.makeText(this,"Clock item clicked",1000)
                    val intent = Intent(this,ProfilActivity::class.java)
                    startActivity(intent);
                    finish();
                    true
                }
                R.id.bottomNavigationStopWatchMenuId -> {
                    val toast = Toast.makeText(this,"Clock item clicked",1000)

                    true
                }
                else -> false
            }
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
            R.id.menu_sign_out ->{
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun verifyUserIsLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun onClickButton(titleEdit:EditText, descriptionEdit:EditText){
        var title = titleEdit.text.toString()
        val description = descriptionEdit.text.toString()
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please enter text in title/description", Toast.LENGTH_SHORT).show()
            return
        }
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/Posts/")

        val post=Post(uid,title,description)

        var newPostRef = ref.push();
        newPostRef.setValue(post)
            .addOnSuccessListener {
                titleEdit.text.clear()
                descriptionEdit.text.clear()
                Log.d(SignupActivity.TAG, "Finally we saved the post to Firebase Database")
            }
            .addOnFailureListener {
                Log.d(SignupActivity.TAG, "Failed to push value to database: ${it.message}")
            }

    }

}
