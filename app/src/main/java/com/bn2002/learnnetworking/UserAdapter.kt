package com.bn2002.learnnetworking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class UserAdapter(private var users: List<UserData>, private val onItemClick: (UserData) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        private val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)

        fun bind(user: UserData) {
            itemView.setOnClickListener { onItemClick(user) }
            nameTextView.text = "Name: ${user.name}"
            emailTextView.text = "Email: ${user.email}"
            addressTextView.text = "Address: ${user.address.street}, ${user.address.city}"
            Picasso.get().load("https://lebavui.github.io${user.avatar.thumbnail}").into(thumbnailImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
    fun updateData(newData: List<UserData>) {
        users = newData
        notifyDataSetChanged()
    }

}