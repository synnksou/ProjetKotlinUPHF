package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import org.bson.Document


class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signup)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextEmail)
        val btn = findViewById<Button>(R.id.btnSignup)
        Stitch.initializeDefaultAppClient(
            resources.getString(R.string.my_app_id)
        )
        val stitchAppClient = Stitch.getDefaultAppClient()

        stitchAppClient.auth.loginWithCredential(AnonymousCredential())
            .addOnSuccessListener {
                System.out.print("yo bg")
            }
    }

    private fun onClickButton(email:String, password:String) {
        val client: StitchAppClient = Stitch.initializeDefaultAppClient("kotlins4-eynak")

        val mongoClient: RemoteMongoClient =
            client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas")

        val coll: RemoteMongoCollection<Document> =
            mongoClient.getDatabase("<DATABASE>").getCollection("<COLLECTION>")

    }

}
