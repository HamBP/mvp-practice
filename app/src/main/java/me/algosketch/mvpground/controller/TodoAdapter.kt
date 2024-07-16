package me.algosketch.mvpground.controller

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.algosketch.mvpground.model.Todo

class TodoAdapter(initialTodos: List<Todo>) : RecyclerView.Adapter<TodoViewHolder>() {
    private val todos = initialTodos.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newTodos: List<Todo>) {
        todos.clear()
        todos.addAll(newTodos)
        notifyDataSetChanged()
    }
}