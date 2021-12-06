package com.letthisbea.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the array lists and the adapter
        val listOfToDoItems = arrayListOf<String>()
        val listItemsAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice,
            listOfToDoItems
        )

        // Adding the items to the list
        // when the ADD button is pressed
        add.setOnClickListener {
            listOfToDoItems.add(new_item_input_field.text.toString())
            todo_list_items_view.adapter = listItemsAdapter
            listItemsAdapter.notifyDataSetChanged()
            new_item_input_field.text.clear()
        }

        // Selecting and Deleting the items from the list
        // when the DELETE button is pressed
        delete.setOnClickListener {
            val arrayOfItemsToDelete : SparseBooleanArray =
                todo_list_items_view.checkedItemPositions
            var lastItemsIndex = todo_list_items_view.count - 1

            while (lastItemsIndex >= 0) {
                if (arrayOfItemsToDelete.get(lastItemsIndex)) {
                    listItemsAdapter.remove(listOfToDoItems.get(lastItemsIndex))
                }
                lastItemsIndex--
            }

            arrayOfItemsToDelete.clear()
            listItemsAdapter.notifyDataSetChanged()
        }

        // Clearing all the items in the list
        // when the CLEAR button is pressed
        clear.setOnClickListener {
            listOfToDoItems.clear()
            listItemsAdapter.notifyDataSetChanged()
        }

        // Adding a toast message on screen
        // when an item on the to-do list is checked
        todo_list_items_view.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(
                this,
                "You selected the item: " + listOfToDoItems.get(i),
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }
}
