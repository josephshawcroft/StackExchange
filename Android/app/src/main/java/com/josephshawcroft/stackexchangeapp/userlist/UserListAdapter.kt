package com.josephshawcroft.stackexchangeapp.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josephshawcroft.stackexchangeapp.data.model.User
import com.josephshawcroft.stackexchangeapp.databinding.ItemUserBinding

interface UserListAdapterListener {

    fun onUserSelected(user: User)
}

class UserListAdapter(private val userAdapterListener: UserListAdapterListener) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var users: ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    fun updateUserList(updatedUsers: List<User>) {
        users.clear()
        users.addAll(updatedUsers)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.reputationText.text = user.reputation.toString()
        holder.displayName.text = user.displayName
    }

    class ViewHolder(binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        val reputationText: TextView = binding.reputationText
        val displayName: TextView = binding.displayName
    }
}