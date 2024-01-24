package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.toBitmap
import com.example.myapplication.DetalleActivity
import com.example.myapplication.R


class MainActivity : AppCompatActivity() {

    private val channelId = "notificacion"
    private val channelName = "Notificación de ejemplo"

    private  var contexto = this


    private  val channelNf2 ="notificacion 2"
    private val channelName2 = "Notificación de ejemplo2"
    private  val channelNF3 ="notificacion3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn3 = findViewById<Button>(R.id.btnNoti3)

        // Configurar el clic del botón para ir a Actividad3
        btn3.setOnClickListener {
            val intent = Intent(this, Actividad3::class.java)
            startActivity(intent)
        }

        // Creamos el canal de notificación si es necesario
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Canal para la primera notificación
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            // Canal para la segunda notificación
            val channel2 = NotificationChannel(channelNf2, channelName2, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel2)
        }

        // Creamos el botón para mostrar la notificación
        val boton = findViewById<Button>(R.id.btnNoti)
        val notificationLayout = RemoteViews(this.packageName, R.layout.activity_noty)
        val notificacionLayout2 =RemoteViews(this.packageName,R.layout.activity_noti2)
        boton.setOnClickListener {
            // Creamos la notificación
            val builder = NotificationCompat.Builder(this, channelId)
            val view = layoutInflater.inflate(R.layout.activity_noty, null)
            val imageView = view.findViewById<AppCompatImageView>(R.id.ivImagen)
            imageView.setImageResource(R.drawable.baseline_public_24)
            builder.setCustomContentView(notificationLayout)
            builder.setSmallIcon(R.drawable.baseline_message_24)
            builder.setContentTitle("Mensaje de prueba")
            builder.setContentText("La persona te ha mandado algo")
            val intent = Intent(this, DetalleActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.setContentIntent(pendingIntent)

            // Mostramos la notificación
            with(contexto.getSystemService<NotificationManager>()){
                this?.notify(1, builder.build())
            }
        }
        // Creamos el botón para mostrar la segunda notificación
        val boton2 = findViewById<Button>(R.id.btnNoti2)
        boton2.setOnClickListener {
            // Creamos la notificación
            val builder2 = NotificationCompat.Builder(this, channelNf2)
            val remoteViews = RemoteViews(this.packageName, R.layout.activity_noti2)

            // Configuramos el contenido de la notificación
            remoteViews.setImageViewResource(R.id.imgNoti2, R.drawable.imgnoti2)

//            builder.setCustomContentView(remoteViews)
            builder2.setSmallIcon(R.drawable.baseline_message_24)
            builder2.setContentTitle("Mensaje de prueba 2")
            builder2.setContentText("La segunda notificación")
            builder2.setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(getDrawable(R.drawable.imgnoti2)!!.toBitmap())

            )
            val intent = Intent(this, DetalleActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            builder2.setContentIntent(pendingIntent)

            // Mostramos la notificación
           with(contexto.getSystemService<NotificationManager>()){
               this?.notify(2, builder2.build())
           }
        }






    }


}


