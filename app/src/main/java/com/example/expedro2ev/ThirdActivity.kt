package com.example.expedro2ev

import DatabaseHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.expedro2ev.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var texto : TextView
    private lateinit var boton : Button
    private lateinit var videoJuegos: VideoJuegos
    private lateinit var atras : Button
    private lateinit var lista : ArrayList<VideoJuegos>
    private lateinit var guardado : Button
    private lateinit var db : DatabaseHelper


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        db = DatabaseHelper(this)

        texto = findViewById(R.id.TodosJuegos)
        boton = findViewById(R.id.siguiente3)
        atras = findViewById(R.id.atras3)
        guardado = findViewById(R.id.guardado)


        lista = intent.getParcelableArrayListExtra<VideoJuegos>("lista") ?: arrayListOf()

        var nombre = intent.getStringExtra("nombre")
        var valoracion = intent.getFloatExtra("valoracion", 0F)
        var empresa = intent.getStringExtra("empresa")
        var fecha = intent.getIntExtra("fecha", 0)

        videoJuegos = VideoJuegos(nombre, valoracion, empresa, fecha)

        var t = ""
        for (lis in lista){
            t = t + lis.toString()
        }
        t = t + videoJuegos.toString()
        texto.text = t

        boton.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            lista.add(videoJuegos)
            intent.putExtra("lista", lista)
            startActivity(intent)

        }

        guardado.setOnClickListener {
            var intent = Intent(this, SaveActivity::class.java)
            lista.add(videoJuegos)
            intent.putExtra("lista", lista)
            for(lis in lista){
                db.escribir(lis)
            }
            startActivity(intent)

        }

        atras.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("nombre", nombre)
            intent.putExtra("valoracion",valoracion)
            intent.putExtra("lista", lista)
            startActivity(intent)
        }



    }


}