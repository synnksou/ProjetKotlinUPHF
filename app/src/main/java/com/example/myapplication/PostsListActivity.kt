package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_layout.view.*
import kotlinx.android.synthetic.main.post_row.view.*
import kotlinx.android.synthetic.main.posts_list_layout.*

class PostsListActivity : AppCompatActivity() {

    public override fun onStart() {
        super.onStart()
        //val currentUser = auth.currentUser

        // Check if user is signed in (non-null) and update UI accordingly.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posts_list_layout)



        val adapter = GroupAdapter<ViewHolder>()

        val bottomNavigationView = findViewById(R.id.bottomNav) as BottomNavigationView


        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.bottomNavigationsHome -> {
                    val intent = Intent(this, HomeAcitivity::class.java)
                    startActivity(intent);
                    finish();
                    true
                }
                R.id.bottomNavigationsSearch -> {
                    val intent = Intent(this, PostsListActivity::class.java)
                    startActivity(intent);
                    finish();
                    true
                }
                R.id.bottomNavigationProfil -> {
                    val toast = Toast.makeText(this, "Clock item clicked", 1000)
                    val intent = Intent(this, ProfilActivity::class.java)
                    startActivity(intent);
                    finish();
                    true
                }
                R.id.bottomNavigationStopWatchMenuId -> {
                    val toast = Toast.makeText(this, "Clock item clicked", 1000)

                    true
                }
                else -> false
            }
        }


   fetchPosts()
    }

    private fun fetchPosts() {
        val ref = FirebaseDatabase.getInstance().getReference("/Posts")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                for (childSnapshot in dataSnapshot.children) {
                    println("testPost$childSnapshot")
                    val post = childSnapshot.getValue(Post::class.java)
                    if(post != null)
                        adapter.add(PostItem(post))
                }
                listOfPost.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
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


}

class PostItem(private val post: Post): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.titleRow.text = post.title
        viewHolder.itemView.textView3.text = post.description

    }

    override fun getLayout(): Int {
        return R.layout.post_row
    }
}

// this is super tedious

//class CustomAdapter: RecyclerView.Adapter<ViewHolder> {
//  override fun onBindViewHolder(p0:, p1: Int) {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//  }
//}
