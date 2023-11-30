package com.example.parcelable
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user : Button = findViewById(R.id.user)
        val dara : User = User("darasow", "dara1998")

        val nom : TextView = findViewById(R.id.nom) as TextView
        val age : TextView = findViewById(R.id.age) as TextView

        nom.setText("login : ${dara?.login}")
        age.setText("password : ${dara?.password}")

        user.setOnClickListener{
            val intent : Intent = Intent(this, Layout::class.java)
//            intent.putExtra("User", dara)
            startActivity(intent)

        }
    }
}