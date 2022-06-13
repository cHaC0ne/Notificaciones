package com.example.notificaciones

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.notificaciones.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
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

}