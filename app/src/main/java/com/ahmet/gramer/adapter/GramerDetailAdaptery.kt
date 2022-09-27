package com.ahmet.gramer.adapter

import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.gramer.databinding.RowGramerDetailsBinding
import com.ahmet.gramer.databinding.RowListBinding
import com.ahmet.gramer.models.Test
import com.ahmet.gramer.view.GramerFragmentDirections
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList


class GramerDetailAdaptery(private val model:ArrayList<Test>): RecyclerView.Adapter<GramerDetailAdaptery.MyViewHolder>() {

    private var tts: TextToSpeech? = null


    class MyViewHolder(val binding: RowGramerDetailsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= RowGramerDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.engText.text=model[position].test_name
        holder.binding.trText.text=model[position].test_turkce

        Glide.with(holder.itemView.context)
            .load(model[position].img_url)
            .into(holder.binding.imageDetails)

        holder.binding.speakButtonDetails.setOnClickListener {

            tts =TextToSpeech(holder.itemView.context, TextToSpeech.OnInitListener {
                if (it == TextToSpeech.SUCCESS) {

                    val result = tts!!.setLanguage(Locale.ENGLISH)
                    tts!!.setSpeechRate(0.7f)
                    tts!!.speak(holder.binding.engText.text.toString(), TextToSpeech.QUEUE_FLUSH, null)

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Belirtilen dil desteklenmiyor")
                    }
                } else {
                    Log.e("TTS", "İnitilation Başarısız")

                }
            })
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