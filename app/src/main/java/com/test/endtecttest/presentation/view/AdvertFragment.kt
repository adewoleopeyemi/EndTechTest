package com.test.endtecttest.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.test.endtecttest.domain.model.Advert
import dagger.hilt.android.AndroidEntryPoint
import endtecttest.R
import endtecttest.databinding.FragmentAdvertBinding


@AndroidEntryPoint
class AdvertFragment : Fragment() {
    private lateinit var binding: FragmentAdvertBinding
    private var ad:Advert? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ad = arguments?.get("ad") as Advert
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_advert, container, false)
        binding.singleAd.setImageResource(ad!!.image)
        binding.heading.text = ad!!.title
        binding.subHeading.text = ad!!.subTitle
        // Inflate the layout for this fragment
        return binding.root
    }
}