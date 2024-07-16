package me.algosketch.mvpground.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoRepository {
    private var nextId = 4
    private val todos = mutableListOf(
        Todo("1", 1, "장보기"),
        Todo("2", 2, "클라이밍 하기"),
        Todo("3", 3, "밥 먹기")
    )

    fun getTodos(): Flow<List<Todo>> = flow {
        emit(todos.toList())
    }

    fun addTodo(todo: String): Flow<Boolean> = flow {
        val id = calculateNextId()
        todos.add(Todo(
            id = id.toString(),
            order = id,
            content = todo,
        ))
        emit(true)
    }

    private fun calculateNextId(): Int {
        return nextId++
    }
}