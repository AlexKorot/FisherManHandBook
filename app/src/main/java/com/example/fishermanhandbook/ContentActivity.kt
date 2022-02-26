package com.example.fishermanhandbook

import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.fishermanhandbook.databinding.ContentLayoutBinding

class ContentActivity:AppCompatActivity() {
    private lateinit var binding:ContentLayoutBinding
    lateinit var face: Typeface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply{
            tvTitle.text=intent.getStringExtra("tvTitle")
            tvContent.text=intent.getStringExtra("tvCont")
            im.setImageResource(intent.getIntExtra("im",R.drawable.shuca))
// установка шрифта
            tvContent.typeface = faceStyle()
          var  title=tvTitle.text
             binding.toolBarContent.title=title

      setSupportActionBar(toolBarContent)
        supportActionBar?.setDisplayHomeAsUpEnabled(true )




        }


    }



  fun faceStyle():Typeface
  {
      face= Typeface.createFromAsset(this.assets,"fonts/Rubik-BoldItalic.ttf")
      return face
  }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home ) finish()
        return true
    }
}