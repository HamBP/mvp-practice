package me.algosketch.mvpground.controller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.algosketch.mvpground.R
import me.algosketch.mvpground.model.TodoRepository

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = TodoAdapter(emptyList())
        val recyclerView = findViewById<RecyclerView>(R.id.rv_todos)
        recyclerView.adapter = adapter

        initData()
    }

    private fun initData() {
        val repository = TodoRepository()
        repository.getTodos()
            .onEach { newTodos ->
                adapter.submitList(newTodos)
            }
            .launchIn(lifecycleScope)
    }
}