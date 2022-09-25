package com.ahmet.gramer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.gramer.databinding.RowListBinding
import com.ahmet.gramer.models.Test
import com.ahmet.gramer.view.GramerFragmentDirections

class GramerAdaptery(private val model:ArrayList<Test>):RecyclerView.Adapter<GramerAdaptery.MyViewHolder>() {

    class MyViewHolder(val binding:RowListBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=RowListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.rowTestName.text=model[position].test_name

        holder.itemView.setOnClickListener {
            val action=GramerFragmentDirections.actionGramerFragmentToQuestionFragment(model[position].id.toInt())
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun getItemCount(): Int {
       return model.size
    }

    fun updateData(updateTestList: List<Test>) {
        model.clear()
        model.addAll(updateTestList)
        notifyDataSetChanged()
    }

}