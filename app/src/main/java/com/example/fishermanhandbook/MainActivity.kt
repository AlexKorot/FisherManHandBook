 package com.example.fishermanhandbook


import android.content.res.TypedArray
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class  MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    var adapter:MyAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolBar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBarContent)
        val nav_view =findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)
        var list=ArrayList<ListItem>()
        list.addAll(fillArras(resources.getStringArray(R.array.fish),
            resources.getStringArray(R.array.fish_content),getImageId(R.array.fish_image_array)))
           adapter=MyAdapter(list,this)
        val rcView = findViewById<RecyclerView>(R.id.rcView)
        rcView.hasFixedSize()
        rcView.layoutManager=LinearLayoutManager(this )
        rcView.adapter=adapter

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true )
        var contentTitle:String=getString(R.string.fish)
        supportActionBar?.title=contentTitle
    }
//функция прослушивает нажатие элементов меню
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.id_Fish -> {
                Toast.makeText(this, "Id Fish" ,Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.fish),
                    resources.getStringArray(R.array.fish_content),getImageId(R.array.fish_image_array)))
                supportActionBar?.title= getString(R.string.fish)
            }
            R.id.id_Bait -> {
                Toast.makeText(this, "Id Bait" ,Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.bait),
                    resources.getStringArray(R.array.bait_content),getImageId(R.array.bait_image_array)))
                supportActionBar?.title= getString(R.string.bait)

        }
            R.id.id_tackle -> Toast.makeText(this, "Id Tackle" ,Toast.LENGTH_SHORT).show()
            R.id.id_history -> Toast.makeText(this, "Id History" ,Toast.LENGTH_SHORT).show()
        }
// закрытие выдвижного меню drawer_layout
    val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
    drawerLayout.closeDrawer(GravityCompat.END)


        return true

    }
    // функция заполняет массив для для єлеметов меню

    fun fillArras(titleArray:Array<String>,contentArray:Array<String>,imageArray:IntArray):List<ListItem>
    {

        var listItemArray = ArrayList<ListItem>()
        for(n in 0..titleArray.size - 1)
        {

            var listItem = ListItem(titleArray[n],contentArray[n],imageArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }
// функция преобразует R.array.fish_image_array в значение Int
    fun getImageId(imageArrayId:Int):IntArray
    {
        var tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices)
        {
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu,menu)
        return true
    }
    // слушатель нажатий для toolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (item.itemId==R.id.menu) { // проверка на какой item в меню произошло нажатие
             // открытие закрытие DrawerLayout кнопкой меню
        if(!drawerLayout.isDrawerOpen(GravityCompat.END))drawerLayout.openDrawer(GravityCompat.END)
             else drawerLayout.closeDrawer(GravityCompat.END)}
        return true
    }
}