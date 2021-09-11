package com.example.mas1stprogrammingassignment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.form.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FormFragment : Fragment() {

    private var fullName = ""
    private var age = 0;
    private var searchText = ""
    private var usedWikiBefore = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_submit).setOnClickListener {
            this.fullName = name.text.toString()
            this.age = ageOfUser.text.toString().toInt()
            this.searchText= multiLine.text.toString()
            this.usedWikiBefore = usedWiki.text.toString()

            findNavController().navigate(R.id.action_formFragment_to_displayFragment)
        }

    }
}