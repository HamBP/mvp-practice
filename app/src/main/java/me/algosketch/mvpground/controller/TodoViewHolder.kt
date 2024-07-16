package me.algosketch.mvpground.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.algosketch.mvpground.R
import me.algosketch.mvpground.model.Todo

class TodoViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
    private val contentTextView: TextView = view.findViewById(R.id.tv_content)
    private val doneCheckBox: CheckBox = view.findViewById(R.id.cb_done)

    fun bind(todo: Todo) {
        contentTextView.text = todo.content
        doneCheckBox.isChecked = todo.isDone
    }

    companion object {
        fun from(parent: ViewGroup): TodoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_todo, parent, false)
            return TodoViewHolder(view)
        }
    }
}