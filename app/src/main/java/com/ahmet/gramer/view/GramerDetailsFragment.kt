package com.ahmet.gramer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmet.gramer.adapter.GramerDetailAdaptery
import com.ahmet.gramer.databinding.FragmentGramerDetailsBinding
import com.ahmet.gramer.viewmodel.GramerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GramerDetailsFragment : Fragment() {

    private var _binding: FragmentGramerDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var  adaptery:GramerDetailAdaptery
    private val args by navArgs<GramerDetailsFragmentArgs>()
    private val viewModel:GramerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGramerDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        when(args.kategoriID) {
            6 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            7 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            8 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            9 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            10 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            11 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            12 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            13 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            14 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            15 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            16 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            17 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            18 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            19 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }
            20 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
            }

        }

    }

    private fun observeLiveData() {
        viewModel.gramerLiveData.observe(viewLifecycleOwner, Observer { details ->
            details?.let {
                adaptery.updateData(details.test)
            }

        })
    }

    private fun initRecycler() {
        binding.recyclerView.layoutManager=LinearLayoutManager(context)
        adaptery= GramerDetailAdaptery(arrayListOf())
        binding.recyclerView.adapter=adaptery
    }


}