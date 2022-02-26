package com.example.fishermanhandbook

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class Logo_Activity : AppCompatActivity() {
    lateinit var face:Typeface
    //переменая для анимации
    lateinit var logoAnim :Animation
    //переменная для image view
    lateinit var imageLogo:ImageView
    lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_logo)
        var tvLogo = findViewById<TextView>(R.id.tvLogo)
        tvLogo.setTypeface( faceStyle())
            //загрузка анимации в переменную
        logoAnim=AnimationUtils.loadAnimation(this,R.anim.logo_anim)
        imageLogo=findViewById(R.id.imageLogo)
        imageLogo.startAnimation(logoAnim)
      //  Toast.makeText(this,"нажмите в любом месте ", Toast.LENGTH_SHORT).show()
   openMainActivity()

    }
    fun faceStyle():Typeface
    {
        face= Typeface.createFromAsset(this.assets,"fonts/Rubik-SemiBold.ttf")
        return face
    }

   // fun onClickActivityMain(view: android.view.View) {


  fun openMainActivity() {
      val i = Intent(this, MainActivity::class.java)
      timer = Timer()
      timer?.schedule(object : TimerTask() {

          override fun run() {
              runOnUiThread { startActivity(i) }
          }


      }, 1000)
  }
    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

}