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
import com.example.hamutils.databinding.FragmentFirstBinding
import java.lang.NumberFormatException

class FirstFragment : Fragment() {

    private fun calc(view: View){
        val result = view.findViewById<TextView>(R.id.PPKCresult)
        val inp: String = view.findViewById<EditText>(R.id.PPKCinput).text.toString()
        /** From ChatGPT
         * val inStr = "12.76/80"
         * val (price, weight) = inStr.split("/").map { it.toFloat() }
        */
        val kg: Int = 1000
        var end: String = "Error"
        try {
            val (price: Float, weightGrams: Float) = inp.split('/').map { it.toFloat() }
            val value: String = String.format("%.2f", (kg / weightGrams) * price)
            end = "\$$value per KG"
        }
        catch (e:NumberFormatException){
            end = "Invalid input"
        }

        result.text = end
    }

    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

      _binding = FragmentFirstBinding.inflate(inflater, container, false)
      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set keyboard listener for the text field.
        val field: EditText = view.findViewById<EditText>(R.id.PPKCinput)
        field.setOnKeyListener(View.OnKeyListener{v, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
            // The true condition
            calc(view)
            // returning false closes the keyboard after pressing enter
            return@OnKeyListener false
        } else {
            false
        }})

        //Run on loss of focus
        field.onFocusChangeListener = View.OnFocusChangeListener{v, hasFocus ->
            if(!hasFocus){
                calc(view)
            }
        }
        binding.PPKCbutton.setOnClickListener {
            calc(view)
        }
    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
    }
}