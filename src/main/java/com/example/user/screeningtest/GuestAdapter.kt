package com.example.user.screeningtest

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.user.screeningtest.Model.Guest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.guest_list.view.*

class GuestAdapter (var context: Context) : RecyclerView.Adapter<GuestHolder>() {

    var guestList: List<Guest> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): GuestHolder {
        return GuestHolder(LayoutInflater.from(context).inflate(R.layout.guest_list, parent, false))
    }

    override fun getItemCount(): Int = guestList.size

    override fun onBindViewHolder(holder: GuestHolder, position: Int) {
        val fullName = "${guestList.get(position).firstName} ${guestList.get(position).lastName}"
        val guestId = "${guestList.get(position).id}"
        holder.itemView.guestName.text = fullName
        holder.itemView.guestId.text = guestId
        Glide.with(context).load(guestList.get(position).avatar).into(holder.itemView.guestImg)

        holder.itemView.setOnClickListener{
            Toast.makeText(context,fullName,Toast.LENGTH_SHORT).show()
            context = holder.itemView.context

            val preferences = context.getSharedPreferences("guestName",0)
            val editor = preferences?.edit()
            editor?.putString("guestName", fullName)
            editor?.apply()
            val intent = Intent(context, Main2Activity::class.java)
            context.startActivity(intent)
        }
    }

    fun setGuestListItems(guestList: List<Guest>){
        this.guestList = guestList
        notifyDataSetChanged()
    }
}