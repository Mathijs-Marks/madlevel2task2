package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val statements = arrayListOf<Statement>()
    private val statementAdapter = StatementAdapter(statements)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {

        createItemTouchHelper().attachToRecyclerView(binding.rvStatements)

        // Initialize the recycler view with a linear layout manager, adapter.
        binding.rvStatements.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvStatements.adapter = statementAdapter
        binding.rvStatements.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        // Populate the statements list and notify the data set has changed.
        for (i in Statement.STATEMENT_TEXTS.indices) {
            statements.add(Statement(Statement.STATEMENT_TEXTS[i], Statement.STATEMENT_ANSWERS[i]))
        }
        statementAdapter.notifyDataSetChanged()
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behaviour (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper.
        val callback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                // The ! in the if statement implies that the statement is false, instead of stating it's the inverse of what you want
                if (direction == ItemTouchHelper.LEFT && !statements[position].statementAnswers ||
                        direction == ItemTouchHelper.RIGHT && statements[position].statementAnswers) {
                    statements.removeAt(position)
                } else {
                    Snackbar.make(binding.rvStatements, R.string.message, Snackbar.LENGTH_LONG).show()
                }
                statementAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}