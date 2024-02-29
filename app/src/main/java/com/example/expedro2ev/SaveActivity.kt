package com.example.expedro2ev

import DatabaseHelper
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SaveActivity : AppCompatActivity() {
    private lateinit var texto : TextView
    private lateinit var atras : Button
    private lateinit var lista : ArrayList<VideoJuegos>
    private lateinit var videoJuegosL : ArrayList<VideoJuegos>
    private lateinit var db : DatabaseHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        lista = intent.getParcelableArrayListExtra<VideoJuegos>("lista") ?: arrayListOf()
        db = DatabaseHelper(this)


        texto = findViewById(R.id.TodosJuegosDB)
        atras = findViewById(R.id.atras4)

        videoJuegosL = db.lectura()
        var t = ""
        for (lis in lista){
            t = t + lis.toString()
        }

        texto.text = t

        atras.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lista", lista)
            startActivity(intent)
        }
    }
}