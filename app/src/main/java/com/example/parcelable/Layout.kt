package com.example.parcelable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import Api
import UserEndpoint
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Layout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        val userList = ArrayList<User>()
        val conexion : TextView = findViewById(R.id.conexion)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://6559e1346981238d054cec1f.mockapi.io/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)

        val login : EditText = findViewById(R.id.login)
        val password : EditText = findViewById(R.id.password)


        conexion.setOnClickListener {

            val loginText : String = login.text.toString().trim()
            val passwordText : String = password.text.toString().trim()

            if (loginText.isEmpty()) {
                // Afficher un style particulier pour le champ Login (par exemple, changer la couleur du texte)
                login.setError("Champ obligatoire")
                return@setOnClickListener
            }
                login.setError(null)
            if (passwordText.isEmpty()) {
                // Afficher un style particulier pour le champ Mot de passe
                password.setError("Champ obligatoire")
                return@setOnClickListener
            }
                password.setError(null)
            val user : User = User(loginText, passwordText)
            var teste : Boolean = false

            api.getUsers().enqueue(object : Callback<List<UserEndpoint>> {
                override fun onResponse(call: Call<List<UserEndpoint>>, response: Response<List<UserEndpoint>>) {
                    if (response.isSuccessful) {
                        val users = response.body()
                        val userExists = checkUserExistence(users, loginText, passwordText)

                        if (userExists) {
                            var intent : Intent = Intent(applicationContext, AfficherDetail::class.java)
                            val user : User = User(loginText, passwordText)
                            intent.putExtra("User", user)
                            startActivity(intent)
                            Log.i("Information", "login : $loginText")
                            Log.i("Information", "password : $passwordText")
                        } else {
                            // L'utilisateur n'existe pas
                            Toast.makeText(applicationContext, "Identiant incorrecte", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Erreur d'accès à l'API : ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<UserEndpoint>>, t: Throwable) {
                    println("Erreur lors de la requête : ${t.message}")
                }
            })


        }



    }
}