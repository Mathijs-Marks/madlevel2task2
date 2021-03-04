package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ItemStatementBinding

class StatementAdapter(private val statements: List<Statement>) : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemStatementBinding.bind(itemView)

        fun dataBind(statement: Statement) {
            binding.tvStatement.text = statement.statementText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return statements.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(statements[position])
    }
}