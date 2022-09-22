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
import com.ahmet.gramer.R
import com.ahmet.gramer.adapter.GramerAdaptery
import com.ahmet.gramer.databinding.FragmentFirstBinding
import com.ahmet.gramer.databinding.FragmentGramerBinding
import com.ahmet.gramer.utils.LoginPref
import com.ahmet.gramer.viewmodel.GramerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GramerFragment : Fragment() {

    private var _binding: FragmentGramerBinding? = null
    private val binding get() = _binding!!

    private val viewModel:GramerViewModel by viewModels()
    private val args by navArgs<GramerFragmentArgs>()
    lateinit var session: LoginPref
    private lateinit var adaptery:GramerAdaptery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        _binding = FragmentGramerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getGramerData(args.kategoriID)

        initRecycler()

        observeLiveData()

        session = LoginPref(requireContext())

        session.checkLogin()

        val user: HashMap<String, String> = session.getUserDetails()

        val username = user.get(LoginPref.key_username)


        binding.gramerNameText.text = "Merhaba $username"

    }

    private fun initRecycler() {
        binding.recyclerGramer.layoutManager=LinearLayoutManager(context)
        adaptery= GramerAdaptery(arrayListOf())
        binding.recyclerGramer.adapter=adaptery
    }

    private fun observeLiveData() {

        viewModel.gramerLiveData.observe(viewLifecycleOwner, Observer { gramer ->
            gramer?.let {
                adaptery.updateData(gramer.test)
            }
        })
    }


}