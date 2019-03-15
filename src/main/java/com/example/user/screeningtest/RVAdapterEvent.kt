package com.example.user.screeningtest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.preference.Preference
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.event_list.view.*

class RVAdapterEvent (var context: Context, private val arrayList: ArrayList<Event>) :
    RecyclerView.Adapter<RVAdapterEvent.Holder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        return Holder(LayoutInflater.from(p0.context).inflate(R.layout.event_list,p0,false))
    }

    override fun getItemCount(): Int{
        return arrayList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val events: Event = arrayList[position]
        holder.view.eventName.text = events.name
        holder.view.eventDate.text = events.date
        holder.view.eventImage.setImageResource(events.picture)

        holder.view.setOnClickListener{
            Toast.makeText(context, events.name, Toast.LENGTH_SHORT).show()
//            val returnIntent = Intent()
//            returnIntent.putExtra("result", events.name)
//            setResult(Activity.RESULT_OK, returnIntent)
//            finish()
            context = holder.view.context
            val preferences = context.getSharedPreferences("eventName",0)
            val editor = preferences?.edit()
            editor?.putString("eventName", events.name)
            editor?.apply()
            val intent = Intent(this.context,Main2Activity::class.java)
            context.startActivity(intent)
        }
    }

    class Holder(val view:View) : RecyclerView.ViewHolder(view)
}

