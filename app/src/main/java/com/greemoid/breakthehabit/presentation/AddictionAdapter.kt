package com.greemoid.breakthehabit.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.greemoid.breakthehabit.data.AddictionModel
import com.greemoid.breakthehabit.databinding.SessionItemLayoutBinding

class AddictionAdapter :
    RecyclerView.Adapter<AddictionAdapter.AddictionViewHolder>() {

    inner class AddictionViewHolder(private val binding: SessionItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(addictionModel: AddictionModel) {
            binding.apply {
                tvBadgeAndDays.text = addictionModel.days
                tvDescription.text = addictionModel.why
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddictionViewHolder {
        val binding =
            SessionItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddictionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddictionViewHolder, position: Int) {
        val model = differ.currentList[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<AddictionModel>() {
        override fun areItemsTheSame(oldItem: AddictionModel, newItem: AddictionModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AddictionModel, newItem: AddictionModel): Boolean {
            return oldItem == newItem
        }
    }


    var onItemClickListener: ((AddictionModel) -> Unit)? = null

    fun setOnClickListener(listener: (AddictionModel) -> Unit) {
        onItemClickListener = listener
    }

    val differ = AsyncListDiffer(this, differCallback)

}