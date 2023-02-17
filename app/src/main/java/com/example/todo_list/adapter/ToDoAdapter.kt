package com.example.todo_list.adapter

import android.graphics.Paint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo_list.R
import com.example.todo_list.data.Modul.Todo

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ItemViewHolder>() {

    private var dataset = mutableListOf<Todo>()

    inner class ItemViewHolder(view: View) : ViewHolder(view) {
        val todoTv: TextView = view.findViewById(R.id.todo_tv)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }

    fun submitList(todoList: MutableList<Todo>) {
        dataset = todoList
        notifyItemInserted(todoList.lastIndex)
    }

    //this function makes a strike through of the text depending on if the checkbox is checked or not,
    //googled this, don't know what an Flag is
    private fun toggleStrikeThroughText(todoTv: TextView, checkBox: Boolean) {
        if (checkBox) {
            todoTv.paintFlags = todoTv.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            todoTv.paintFlags = todoTv.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun deleteTodo(position: Int){
       dataset.removeAt(position)
       notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return ItemViewHolder(itemLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val todo = dataset[position]

        holder.todoTv.text = todo.string
        holder.checkBox.isChecked = todo.done

        //sets the toggle status
        toggleStrikeThroughText(holder.todoTv, todo.done)
        //changes the toggle status
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThroughText(holder.todoTv, isChecked)
            todo.done = !todo.done
        }
    }
}