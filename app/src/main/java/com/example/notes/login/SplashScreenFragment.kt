package com.example.notes.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.databinding.FragmentSpashScreenBinding

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSpashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_spash_screen,
            container,
            false
        )

        val ivNote = binding.ivNote
        ivNote.alpha = 0f
        ivNote.animate().setDuration(1500).alpha(1f).withEndAction{
           findNavController().navigate(R.id.action_splashScreenFragment_to_signInFragment)
        }
        return binding.root
    }

}