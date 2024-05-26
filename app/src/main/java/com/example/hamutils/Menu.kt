package com.example.hamutils

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hamutils.databinding.MainMenuBinding
import java.lang.NumberFormatException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Menu : Fragment() {
    private fun wan2words (view: View){
        var digits: Int = 0
    }
    private var _binding: MainMenuBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MainMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.MenuButton1.setOnClickListener {
            findNavController().navigate(R.id.action_MM_to_FF)
        }
        binding.MenuButton2.setOnClickListener {
            findNavController().navigate(R.id.action_MM_to_WC)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}