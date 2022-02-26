package com.example.fishermanhandbook

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(listArray:ArrayList<ListItem>, context:Context):RecyclerView.Adapter<MyAdapter.ViewHolder>()
{
    var listArrayR=listArray
    var contextR=context
    lateinit var face: Typeface
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.textTitle)
        val tvCont = view.findViewById<TextView>(R.id.textContent)
        val im = view.findViewById<ImageView>(R.id.imContent)
    // прослушивание нажатий и заполнение itema  в recyclerView

        fun bind(listitem: ListItem, context: Context) {

            var tvCon=listitem.contentText.substring(0,60)+"..."
            var face= Typeface.createFromAsset(context.assets,"fonts/Rubik-SemiBold.ttf")
            tvTitle.text=listitem.titleText
            tvTitle.setTypeface(face)
            tvCont.text=tvCon
             tvCont.setTypeface(face)
            im.setImageResource(listitem.image_Id)
            itemView.setOnClickListener(){
                Toast.makeText( context,"Pressed ${tvTitle.text}", Toast.LENGTH_SHORT).show()
                val i=Intent(context,ContentActivity::class.java).apply{
                    putExtra("tvTitle", tvTitle.text.toString())
                    putExtra("tvCont", listitem.contentText. toString())
                    putExtra("im",listitem.image_Id)
                }
                context.startActivity(i)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater= LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
             var listitem=listArrayR.get(position)
        holder.bind(listitem,contextR)
    }

    override fun getItemCount(): Int {
       return listArrayR.size
    }
    fun updateAdapter(listArray:List<ListItem>){
        listArrayR.clear()
        listArrayR.addAll(listArray)
        notifyDataSetChanged()
    }

}

