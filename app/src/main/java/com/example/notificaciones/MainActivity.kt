package com.example.notificaciones

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.notificaciones.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private val CHANNEL_ID = "channel_id"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)


    }

    fun myToast(view: View) {
        Toast.makeText(this@MainActivity, "Toast Standar", Toast.LENGTH_LONG).show()
    }

    fun myToastGravity(view: View) {
        val t = Toast.makeText(this@MainActivity, "Toast Gravity", Toast.LENGTH_SHORT)
        t.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        t.show()
    }

    fun myToastLayout(view: View){
        val layout = layoutInflater.inflate(R.layout.toast,null )
        layout!!.findViewById<TextView>(R.id.tvSnackTitulo).text = "Alerta"
        layout!!.findViewById<TextView>(R.id.tvSnackDescripcion).text = "Esto es un Toast Personalizado"
        val t = Toast(this@MainActivity)
        t.duration = Toast.LENGTH_SHORT
        t.view = layout
        t.show()

    }

    fun mySnackBar(view: View){

            Snackbar.make(view,"Aviso con Snackbar!", Snackbar.LENGTH_SHORT).show()

    }
    fun snackColor (view: View){
        val s = Snackbar.make(view, "Test SnackBar", Snackbar.LENGTH_SHORT)
            s.setTextColor(ContextCompat.getColor(view.context, R.color.white))
            s.setActionTextColor(Color.YELLOW)
            s.setBackgroundTint(Color.BLUE)
            s.show()
    }
    fun mySnackCustom(view: View){
        val s = Snackbar.make(view,"",Snackbar.LENGTH_SHORT)
        val sbLayout = s.view as Snackbar.SnackbarLayout
        val customLayout = layoutInflater.inflate (R.layout.custom_snackbar, null)
        customLayout!!.findViewById<TextView>(R.id.tvSnackTitulo).text = "ALERTA"
        customLayout!!.findViewById<TextView>(R.id.tvSnackDescripcion).text = "Esto es una SnackBar Personalizado"
        sbLayout.addView(customLayout,0)
        s.setBackgroundTint(Color.YELLOW)
        s.show()
    }
    fun alertDialog(view: View){
        AlertDialog.Builder(view.context).setTitle("Aviso").setMessage("Esto es una mensaje de alerta")
            .setIcon(R.drawable.alerta).setPositiveButton("Aceptar") { dialog, id -> /* Aceptar */ }
            .show()

    }
    fun selccionDialog(view: View){
        val items = arrayOf("Perro", "Gato", "Caballo")
        AlertDialog.Builder(this).setTitle("Seleccion un animal").setMultiChoiceItems(items, null)
        {
                dialog, which, isChecked -> Log.i("Dialogos",
                "Opcion" + items[which] + "marcada a " + isChecked)
        }
            .setNegativeButton(R.string.cancelar, null)
            .setPositiveButton(R.string.aceptar, null)
            .show()
    }

    fun customDialog(view: View){
        val inflater = this.layoutInflater
        val custom_layout = inflater.inflate(R.layout.layout_dialog, null)
        AlertDialog.Builder(this).setView(custom_layout)
            .setPositiveButton(R.string.aceptar, null)
            .setNegativeButton(R.string.cancelar, null)
            .show()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notificacionTitulo)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = getString(R.string.notificacionContent)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)


        }
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    fun notificacion(view: View) {
        createNotificationChannel()


        val builder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icono_not)
                .setContentTitle("EEEY")
                .setContentText("Ejemplo de notificacion")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val intent = Intent(this, ActividadResultado::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)


        val NOTIF_ID = 1
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIF_ID, builder.build())
    }




}