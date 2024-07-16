package me.algosketch.mvpground.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoRepository {
    private var nextId = 1
    private val todos = mutableListOf<Todo>()

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