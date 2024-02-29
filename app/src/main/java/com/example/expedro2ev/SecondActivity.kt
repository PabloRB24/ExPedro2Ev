package com.example.expedro2ev

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    private lateinit var empresa : EditText
    private lateinit var fecha : EditText
    private lateinit var boton : Button
    private lateinit var atras : Button
    private lateinit var lista : ArrayList<VideoJuegos>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        empresa = findViewById(R.id.empresa)
        fecha = findViewById(R.id.fecha)
        boton = findViewById(R.id.siguiente2)
        atras = findViewById(R.id.atras2)
        var nombre = intent.getStringExtra("nombre")
        var valoracion = intent.getFloatExtra("valoracion", 0F)

        lista = intent.getParcelableArrayListExtra<VideoJuegos>("lista") ?: arrayListOf()

        boton.setOnClickListener {
            var intent = Intent(this, ThirdActivity::class.java)

            if(empresa.text.isNotEmpty() && fecha.text.isNotEmpty()){
                if (fecha.text.toString().toIntOrNull() != null){
                    intent.putExtra("empresa", empresa.text.toString())
                    intent.putExtra("fecha", fecha.text.toString().toInt())
                    intent.putExtra("nombre",nombre)
                    intent.putExtra("valoracion",valoracion)
                    intent.putExtra("lista", lista)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"El año tiene que ser un Int",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Campos vacios", Toast.LENGTH_SHORT).show()
            }

        }

        atras.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lista", lista)
            startActivity(intent)

        }



    }
}