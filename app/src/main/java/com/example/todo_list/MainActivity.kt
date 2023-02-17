package com.example.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todo_list.adapter.ToDoAdapter
import com.example.todo_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = ToDoAdapter()
        val swipeLeft = ItemTouchHelper(SwipeToDelete(adapter))

        binding.recyclerView.adapter = adapter
        swipeLeft.attachToRecyclerView(binding.recyclerView)

        viewModel.todos.observe(this) {
            adapter.submitList(it)
        }

        binding.addBtn.setOnClickListener {
            if (!binding.textInputEdit.text.isNullOrEmpty()){
                viewModel.addTodo(binding.textInputEdit.text.toString())
                binding.textInputEdit.text = null

                //closes the software keyboard
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
            }
        }
    }
}