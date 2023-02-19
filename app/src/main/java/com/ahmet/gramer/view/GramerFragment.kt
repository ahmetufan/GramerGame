package com.ahmet.gramer.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmet.gramer.BuildConfig
import com.ahmet.gramer.R
import com.ahmet.gramer.adapter.GramerAdaptery
import com.ahmet.gramer.databinding.FragmentGramerBinding
import com.ahmet.gramer.utils.LoginPref
import com.ahmet.gramer.viewmodel.GramerViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GramerFragment : Fragment() {

    private var _binding: FragmentGramerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GramerViewModel by viewModels()
    private val args by navArgs<GramerFragmentArgs>()
    lateinit var session: LoginPref
    private lateinit var adaptery: GramerAdaptery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGramerBinding.inflate(inflater, container, false)
        val view = binding.root

        // AdView Initialize
        MobileAds.initialize(requireContext()) {}

        val adView = AdView(requireContext())
        adView.setAdSize(AdSize.LARGE_BANNER)
        adView.adUnitId = BuildConfig.GRAMER_FRAGMENT_BANNER
        binding.rlAdsContainer.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (args.kategoriID) {
            1 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()


            }
            2 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
                binding.collapsingToolbar.background =requireActivity().getDrawable(R.drawable.cumle_bck)
                binding.coordinatorLayout.background=requireActivity().getDrawable(R.drawable.ctr_bg2)
                binding.nestedScrool.background=requireActivity().getDrawable(R.drawable.layout_bg2)
                binding.imageGramer.background=requireActivity().getDrawable(R.drawable.workgramer3)

            }
            3 -> {
                viewModel.getGramerData(args.kategoriID)
                initRecycler()
                observeLiveData()
                binding.collapsingToolbar.background =requireActivity().getDrawable(R.drawable.karisikarkabck)
                binding.coordinatorLayout.background=requireActivity().getDrawable(R.drawable.ctr_bg3)
                binding.nestedScrool.background=requireActivity().getDrawable(R.drawable.layout_bg3)
                binding.imageGramer.background=requireActivity().getDrawable(R.drawable.karisikarka)

            }

        }

        pref()

    }

    private fun initRecycler() {
        binding.recyclerGramer.layoutManager = LinearLayoutManager(context)
        adaptery = GramerAdaptery(arrayListOf())
        binding.recyclerGramer.adapter = adaptery
    }

    private fun observeLiveData() {

        viewModel.gramerLiveData.observe(viewLifecycleOwner, Observer { gramer ->
            gramer?.let {
                adaptery.updateData(gramer.test)
            }
        })
    }

    private fun pref() {

        session = LoginPref(requireContext())

        session.checkLogin()

        val user: HashMap<String, String> = session.getUserDetails()

        val username = user.get(LoginPref.key_username)


        binding.gramerNameText.text = "Merhaba $username"
    }


}