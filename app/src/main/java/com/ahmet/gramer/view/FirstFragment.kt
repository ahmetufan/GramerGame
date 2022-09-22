package com.ahmet.gramer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ahmet.gramer.R
import com.ahmet.gramer.databinding.FragmentFirstBinding
import com.ahmet.gramer.utils.LoginPref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    lateinit var session: LoginPref

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        session = LoginPref(requireContext())
        if (session.isLoggedIn()) {
            findNavController().navigate(R.id.action_firstFragment_to_homeFragment)
        }

        binding.button.setOnClickListener {
            val username = binding.input.text.toString().trim()

            if (username.isEmpty()) {
                binding.button.isEnabled
            } else {

                session.createLoginSession(username)
                val action =
                    FirstFragmentDirections.actionFirstFragmentToHomeFragment(binding.input.text.toString())
                Navigation.findNavController(view).navigate(action)
            }
        }


    }


}