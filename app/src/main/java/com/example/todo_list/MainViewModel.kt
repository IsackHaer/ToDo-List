package com.example.todo_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo_list.data.ToDoRepository

class MainViewModel: ViewModel() {

    private val repository = ToDoRepository()
    val todos = repository.todos

//    init {
//        repository.todos
//    }

    fun addTodo(string: String){
        repository.addTodo(string)
    }
}