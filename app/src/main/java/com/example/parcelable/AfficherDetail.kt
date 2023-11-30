package com.example.parcelable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AfficherDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_afficher_detail)

        val user = intent.getParcelableExtra<User>("User")
        val nom : TextView = findViewById(R.id.login) as TextView
        val age : TextView = findViewById(R.id.password) as TextView
        nom.setText("Login : ${user?.login}")
        age.setText("Password : ${user?.password}")

    }
}