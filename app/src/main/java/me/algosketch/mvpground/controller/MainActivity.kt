package me.algosketch.mvpground.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

/**
 * MVC의 Controller에서 사용자 입력을 받아 model과 상호작용하고 결과를 View에 전달한다.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: TodoAdapter
    private val repository by lazy { TodoRepository() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUi()
        initData()
    }

    private fun initUi() {
        adapter = TodoAdapter(emptyList())
        val recyclerView = findViewById<RecyclerView>(R.id.rv_todos)
        recyclerView.adapter = adapter
        val submitButton = findViewById<Button>(R.id.btn_submit)
        submitButton.setOnClickListener { // 사용자 입력을 처리하므로 Controller 로직
            addTask()
        }
    }

    private fun initData() {
        repository.getTodos() // 모델에서 데이터 호출
            .onEach { newTodos ->
                adapter.submitList(newTodos)
            }
            .launchIn(lifecycleScope)
    }

    /**
     * 사용자 입력을 받고(Controller 역할) Model에 전달
     */
    private fun addTask() {
        val newTaskEditText = findViewById<EditText>(R.id.et_new_task)
        val newTask = newTaskEditText.text.toString()
        if (newTask.isNotBlank()) {
            repository.addTodo(newTask).launchIn(lifecycleScope)
            initData() // TODO: 수정하시오
        }
        newTaskEditText.text.clear()
    }
}