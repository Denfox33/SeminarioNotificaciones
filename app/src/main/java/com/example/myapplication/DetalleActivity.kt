package com.example.myapplication


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noty)

        // Obtener los datos de la notificación desde la intención
        val intent = intent
        val extras = intent.extras

        // Mostrar los datos de la notificación
        val tvMensaje1 = findViewById<TextView>(R.id.tvMensaje1)
        tvMensaje1.text = extras?.getString("mensaje1")

        val tvMensaje2 = findViewById<TextView>(R.id.tvMensaje2)
        tvMensaje2.text = extras?.getString("mensaje2")

        val ivImagen = findViewById<ImageView>(R.id.ivImagen)
        ivImagen.setImageResource(extras?.getInt("imagen") ?: 0)
    }
}