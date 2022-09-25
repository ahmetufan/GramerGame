package com.ahmet.gramer.view

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.ahmet.gramer.databinding.FragmentGramerQuizListBinding
import com.ahmet.gramer.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GramerQuizListFragment : Fragment() {

    private var _binding: FragmentGramerQuizListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private var tts: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGramerQuizListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getHomeData()

        viewModel.homeLiveData.observe(viewLifecycleOwner, Observer { gramerList ->

            gramerList?.let {

                gramerList.kategori.forEach {

                    if (it.id == "3") {
                        binding.hayvanlarTextName.text = it.name
                    } else if (it.id == "4") {
                        binding.mesleklerTextName.text = it.name
                    }
                }
            }
        })

        binding.btnlist.setOnClickListener {
            val action = GramerQuizListFragmentDirections.actionGramerListFragmentToGramerFragment(1)
            Navigation.findNavController(it).navigate(action)
        }

        binding.imageHayvanlar.setOnClickListener {
            val action2 = GramerQuizListFragmentDirections.actionGramerListFragmentToGramerFragment(3)
            Navigation.findNavController(it).navigate(action2)
        }

        binding.imageMeslekler.setOnClickListener {
            val action3 = GramerQuizListFragmentDirections.actionGramerListFragmentToGramerFragment(4)
            Navigation.findNavController(it).navigate(action3)
        }


        binding.b1.setOnClickListener {

            tts = TextToSpeech(requireActivity(), TextToSpeech.OnInitListener {
                if (it == TextToSpeech.SUCCESS) {

                    val result = tts!!.setLanguage(Locale.ENGLISH)
                    tts!!.setSpeechRate(0.7f)
                    tts!!.speak(binding.e1.text.toString(), TextToSpeech.QUEUE_FLUSH, null)

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Belirtilen dil desteklenmiyor")
                    }

                } else {
                    Log.e("TTS", "İnitilation Başarısız")

                }
            })
        }


    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}