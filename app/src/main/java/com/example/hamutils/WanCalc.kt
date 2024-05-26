package com.example.hamutils;

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.hamutils.databinding.FragmentFirstBinding
import com.example.hamutils.databinding.WanCalcBinding
import java.lang.NumberFormatException

public class WanCalc : Fragment() {
    private fun wan2words(view: View) {
        val result = view.findViewById<TextView>(R.id.WCresult) //update for wancalc
        val inp: String = view.findViewById<EditText>(R.id.WCinput).text.toString()
        var multiplier: String = view.findViewById<Spinner>(R.id.WCspinner).selectedItem.toString()
        val mm = mapOf<String, Int>("一" to 1, "万" to 10000, "亿" to 100000000)
        val names = listOf<Pair<String, Int>>(  Pair("",0), //zero char
                                                Pair("",-1), //ones
                                                Pair("",-1), //tens
                                                Pair("hundred",1),
                                                Pair("thousand",1),
                                                Pair("thousand",2),
                                                Pair("hundred thousand",1),
                                                Pair("million",1),
                                                Pair("million",2),
                                                Pair("hundred million",1),
                                                Pair("billion",1),
                                                Pair("billion",2),
                                                Pair("hundred billion",1),
                                                Pair("trillion",1),
                                                Pair("trillion",2),
                                                Pair("hundred trillion",1))
        var op: String = ""
        try {
            var number: String = (inp.toInt() * mm[multiplier]!!).toString()
            var decimal: String = ""
            val splits = number.split('.')
            number = splits[0]
            if (splits.size > 1){ decimal = splits[1] }
            val digits: Pair<String, Int> = names[number.length]
            op = "${number.slice(0 until digits.second)}.${number.slice(digits.second until number.length).trimEnd('0')} ${digits.first}"
        }
        catch (e: NumberFormatException){
            op = "Number format error!"
        }

        result.text = op

    }

    private var _binding: WanCalcBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = WanCalcBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set keyboard listener for the text field.
        val field: EditText = view.findViewById<EditText>(R.id.WCinput)
        field.setOnKeyListener(View.OnKeyListener{ v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                // The true condition
                wan2words(view)
                // returning false closes the keyboard after pressing enter
                return@OnKeyListener false
            } else {
                false
            }})

        //Run on loss of focus
        field.onFocusChangeListener = View.OnFocusChangeListener{ v, hasFocus ->
            if(!hasFocus){
                wan2words(view)
            }
        }
        binding.WCbutton.setOnClickListener {
            wan2words(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
