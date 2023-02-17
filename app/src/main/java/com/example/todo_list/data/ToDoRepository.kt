package com.example.todo_list.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todo_list.data.Modul.Todo

class ToDoRepository {

    private var _todos = MutableLiveData<MutableList<Todo>>(mutableListOf())
    val todos: LiveData<MutableList<Todo>> = _todos

    fun addTodo(todo: String){
        _todos.value?.add( Todo(todo, false))
        _todos.value = _todos.value
    }
}