package com.ahmet.gramer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.ahmet.gramer.BuildConfig
import com.ahmet.gramer.databinding.FragmentGramerQuizListBinding
import com.ahmet.gramer.utils.LoginPref
import com.ahmet.gramer.viewmodel.HomeViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GramerQuizListFragment : Fragment() {

    private var _binding: FragmentGramerQuizListBinding? = null
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
        _binding = FragmentGramerQuizListBinding.inflate(inflater, container, false)
        val view = binding.root

        // AdView Initialize
        MobileAds.initialize(requireContext()) {}

        val adView = AdView(requireContext())
        adView.setAdSize(AdSize.LARGE_BANNER)
        adView.adUnitId = BuildConfig.GRAMER_QUIZLISTFRAGMENT_BANNER
        binding.rlAdsContainer.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getHomeData()

        viewModel.homeLiveData.observe(viewLifecycleOwner, Observer { gramerList ->

            gramerList?.let {

                gramerList.kategori.forEach {

                    if (it.id == "3") {
                        binding.karisiktextname.text = it.name
                    } else if (it.id == "4") {
                        binding.fiillertextname.text = it.name
                    }
                }
            }
        })

        pref()

        binding.imageFiil.setOnClickListener {
            val action = GramerQuizListFragmentDirections.actionGramerListFragmentToGramerFragment(1)
            Navigation.findNavController(it).navigate(action)
        }

        binding.imageHayvanlar.setOnClickListener {
            val action2 = GramerQuizListFragmentDirections.actionGramerListFragmentToGramerFragment(3)
            Navigation.findNavController(it).navigate(action2)
        }

    }

    private fun pref() {

        session = LoginPref(requireContext())

        session.checkLogin()

        val user: HashMap<String, String> = session.getUserDetails()

        val username = user.get(LoginPref.key_username)

        binding.personNameTextList.text = "Merhaba $username"
    }


}