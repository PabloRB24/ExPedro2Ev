package com.example.expedro2ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nombre : EditText
    private lateinit var valoracion : EditText
    private lateinit var boton : Button

    private lateinit var lista : ArrayList<VideoJuegos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombre = findViewById(R.id.nombre)
        valoracion = findViewById(R.id.valoracion)
        boton = findViewById(R.id.siguiente1)

        lista = intent.getParcelableArrayListExtra<VideoJuegos>("lista") ?: arrayListOf()


        boton.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)

            if(nombre.text.isNotEmpty() && valoracion.text.isNotEmpty()){
                if (valoracion.text.toString().toFloatOrNull() != null){
                    if (valoracion.text.toString().toFloat() >= 0F){
                        intent.putExtra("nombre", nombre.text.toString())
                        intent.putExtra("valoracion", valoracion.text.toString().toFloat())
                        intent.putExtra("lista", lista)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"La validacion tiene que ser >= 0F",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"La valoracion tiene que ser un Float",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show()
            }
        }


    }
}

