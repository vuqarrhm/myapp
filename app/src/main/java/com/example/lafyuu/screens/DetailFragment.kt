package com.example.lafyuu.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.lafyuu.R
import com.example.lafyuu.databinding.FragmentDetailBinding
import com.example.lafyuu.util.loadImage
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
     private val args:DetailFragmentArgs by navArgs()
    lateinit var binding:FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView11.text=args.deyer.description
        args.deyer.images?.let {
            if (it.size>1){
                Picasso.get().load(it[0]).into(binding.imageView7);

            }

        }

    }

}
