package com.ahmet.gramer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ahmet.gramer.R
import com.ahmet.gramer.databinding.FragmentGramerBinding
import com.ahmet.gramer.databinding.FragmentGramerDetailsListBinding
import com.ahmet.gramer.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GramerDetailsListFragment : Fragment() {

    private var _binding: FragmentGramerDetailsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGramerDetailsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHomeData()

        viewModel.homeLiveData.observe(viewLifecycleOwner, Observer { details ->

            details?.let {

                details.kategori.forEach {

                    if (it.id == "6") {
                        binding.vucudText.text = it.name
                    } else if (it.id == "7") {
                        binding.gidaText.text = it.name
                    } else if (it.id == "8") {
                        binding.hayvanlarText.text = it.name
                    } else if (it.id == "9") {
                        binding.mevsimlerText.text = it.name
                    } else if (it.id == "10") {
                        binding.sayilarText.text = it.name
                    } else if (it.id == "11") {
                        binding.esyalarText.text = it.name
                    } else if (it.id == "12") {
                        binding.renklerText.text = it.name
                    } else if (it.id == "13") {
                        binding.sporText.text = it.name
                    } else if (it.id == "14") {
                        binding.duyguText.text = it.name
                    } else if (it.id == "15") {
                        binding.meslekText.text = it.name
                    } else if (it.id == "16") {
                        binding.ulkeText.text = it.name
                    }else if (it.id == "17") {
                        binding.giyimText.text = it.name
                    }else if (it.id == "18") {
                        binding.bitkiText.text = it.name
                    }else if (it.id == "19") {
                        binding.saglKText.text = it.name
                    }else if (it.id == "20") {
                        binding.aileText.text = it.name
                    }
                }
            }
        })

        sendNavigate()

    }
    private fun sendNavigate() {
        binding.vucudcard.setOnClickListener {
            val vucud =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    6
                )
            Navigation.findNavController(it).navigate(vucud)
        }
        binding.gidacard.setOnClickListener {
            val gida =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    7
                )
            Navigation.findNavController(it).navigate(gida)
        }
        binding.hayvancard.setOnClickListener {
            val hayvan =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    8
                )
            Navigation.findNavController(it).navigate(hayvan)
        }
        binding.mevsimcard.setOnClickListener {
            val mevsim =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    9
                )
            Navigation.findNavController(it).navigate(mevsim)
        }
        binding.sayicard.setOnClickListener {
            val sayi =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    10
                )
            Navigation.findNavController(it).navigate(sayi)
        }
        binding.esyacard.setOnClickListener {
            val esya =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    11
                )
            Navigation.findNavController(it).navigate(esya)
        }
        binding.renkcard.setOnClickListener {
            val renk =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    12
                )
            Navigation.findNavController(it).navigate(renk)
        }
        binding.sporcard.setOnClickListener {
            val spor =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    13
                )
            Navigation.findNavController(it).navigate(spor)
        }
        binding.duygucard.setOnClickListener {
            val duygu =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    14
                )
            Navigation.findNavController(it).navigate(duygu)
        }
        binding.meslekcard.setOnClickListener {
            val meslek =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    15
                )
            Navigation.findNavController(it).navigate(meslek)
        }
        binding.ulkecard.setOnClickListener {
            val ulke =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    16
                )
            Navigation.findNavController(it).navigate(ulke)
        }
        binding.giyimcard.setOnClickListener {
            val giyim =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    17
                )
            Navigation.findNavController(it).navigate(giyim)
        }
        binding.bitkicard.setOnClickListener {
            val bitki =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    18
                )
            Navigation.findNavController(it).navigate(bitki)
        }
        binding.saglikcard.setOnClickListener {
            val saglik =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    19
                )
            Navigation.findNavController(it).navigate(saglik)
        }
        binding.ailecard.setOnClickListener {
            val aile =
                GramerDetailsListFragmentDirections.actionGramerDetailsListFragmentToGramerDetailsFragment(
                    20
                )
            Navigation.findNavController(it).navigate(aile)
        }



    }
}