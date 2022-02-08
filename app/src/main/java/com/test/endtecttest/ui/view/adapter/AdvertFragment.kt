package com.test.endtecttest.ui.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import endtecttest.R
import endtecttest.databinding.FragmentAdvertBinding


class AdvertFragment(image: Int) : Fragment() {
    private lateinit var binding: FragmentAdvertBinding
    private var image: Int = image
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_advert, container, false)

        binding.singleAd.setImageResource(image)
        // Inflate the layout for this fragment
        return binding.root
    }
}