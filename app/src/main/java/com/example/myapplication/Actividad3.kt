package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class Actividad3 : AppCompatActivity() {

    private val channelId = "miCanalNotificacion"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        // Obtener referencias a los elementos de la interfaz de usuario
        val editTextTitulo: EditText = findViewById(R.id.editTextTitulo)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion)
        val spinnerIcono: Spinner = findViewById(R.id.spinnerIcono)
        val spinnerImagen: Spinner = findViewById(R.id.spinnerImagen)
        val textViewNumeroBotones: TextView = findViewById(R.id.textViewNumeroBotones)
        val btnDecremento: Button = findViewById(R.id.btnDecremento)
        val btnIncremento: Button = findViewById(R.id.btnIncremento)
        val btnEnviarNotificacion: Button = findViewById(R.id.btnEnviarNotificacion)

        // Configurar el Spinner para seleccionar el icono
        val iconos = arrayOf(R.drawable.baseline_public_24, R.drawable.baseline_message_24)
        val iconoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, iconos)
        spinnerIcono.adapter = iconoAdapter

        // Configurar el Spinner para seleccionar la imagen
        val imagenes = arrayOf(R.drawable.imgnoti2, R.drawable.imgnoti2)
        val imagenAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, imagenes)
        spinnerImagen.adapter = imagenAdapter

        // Configurar los botones de incremento y decremento
        var numeroBotones = 3 // Valor inicial
        textViewNumeroBotones.text = numeroBotones.toString()

        btnDecremento.setOnClickListener {
            if (numeroBotones > 1) {
                numeroBotones--
                textViewNumeroBotones.text = numeroBotones.toString()
            }
        }

        btnIncremento.setOnClickListener {
            numeroBotones++
            textViewNumeroBotones.text = numeroBotones.toString()
        }

        // Llamar a la función para crear el canal de notificación
        createNotificationChannel()

        // Configurar el clic del botón "Enviar notificación"
        btnEnviarNotificacion.setOnClickListener {
            // Obtener el texto de los EditText
            val titulo = editTextTitulo.text.toString()
            val descripcion = editTextDescripcion.text.toString()

            // Configurar la notificación personalizada con los valores ingresados por el usuario
            val notificationLayout = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_message_24)
                .setCustomContentView(createCustomNotificationView(titulo, descripcion))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            // Mostrar la notificación
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notificationLayout)
        }
    }

    private fun createCustomNotificationView(titulo: String, descripcion: String): RemoteViews {
        // Configurar la vista personalizada para la notificación
        val customNotificationView = RemoteViews(packageName, R.layout.activity_noti3)

        // Actualizar las vistas según sea necesario
        customNotificationView.setTextViewText(R.id.idtitulo, titulo)
        customNotificationView.setTextViewText(R.id.idDescripcion, descripcion)
        customNotificationView.setImageViewResource(R.id.imgIcono, R.drawable.baseline_public_24)
        customNotificationView.setImageViewResource(R.id.imgNoti2, R.drawable.imgnoti2)

        return customNotificationView
    }

    private fun createNotificationChannel() {
        // Crear el canal de notificación si es necesario (solo necesario en Android 8.0 y versiones posteriores)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Nombre del canal",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}