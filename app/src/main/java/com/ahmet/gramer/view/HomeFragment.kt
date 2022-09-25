package com.ahmet.gramer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ahmet.gramer.R
import com.ahmet.gramer.databinding.FragmentHomeBinding
import com.ahmet.gramer.utils.LoginPref
import com.ahmet.gramer.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    lateinit var session: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getHomeData()


        viewModel.homeLiveData.observe(viewLifecycleOwner, Observer { livedata ->


            livedata.let {

                livedata.kategori.forEach {

                    if (it.id == "1") {
                        binding.HomeGramerQuizText.text = it.name

                    } else if (it.id == "2") {
                        binding.homeCumleText.text = it.name
                    }
                    else if (it.id == "5") {
                        binding.homeGramerText.text= it.name
                    }
                }

            }
        })

        pref()

        binding.profilImage.setOnClickListener {

            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("")
            alert.setMessage("Çıkış yapmak istiyor musun ?")
            alert.setPositiveButton("Evet") { dialog, which ->
                session.LogoutUser()
                // Restart
                findNavController().navigate(R.id.action_homeFragment_to_firstFragment)
                activity?.finish()

            }
            alert.setNegativeButton("Hayır") { dialog, which ->
            }
            alert.show()
        }

        binding.gramerImageview.setOnClickListener {

            val action=HomeFragmentDirections.actionHomeFragmentToGramerListFragment()
            Navigation.findNavController(it).navigate(action)
        }


        binding.cumleImageview.setOnClickListener {
            val action2 = HomeFragmentDirections.actionHomeFragmentToGramerFragment(2)
            Navigation.findNavController(it).navigate(action2)
        }

        binding.gramerDetailsImage.setOnClickListener {

            val action3=HomeFragmentDirections.actionHomeFragmentToGramerDetailsListFragment()
            Navigation.findNavController(it).navigate(action3)
        }


    }


    private fun pref() {

        session = LoginPref(requireContext())

        session.checkLogin()

        val user: HashMap<String, String> = session.getUserDetails()

        val username = user.get(LoginPref.key_username)

        binding.personNameText.text = "Merhaba $username"
    }


}