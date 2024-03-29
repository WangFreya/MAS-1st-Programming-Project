package com.example.mas1stprogrammingassignment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.form.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FormFragment : Fragment() {

    private var fullName = ""
    private var age = 0;
    private var searchText = ""
    private var usedWikiBefore = ""
    val db = Firebase.firestore

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
            fullName = name.text.toString()
            age = ageOfUser.text.toString().toInt()
            searchText= multiLine.text.toString()
            usedWikiBefore = usedWiki.text.toString()

            val user = hashMapOf(
                    "name" to fullName,
                    "age" to this.age,
                    "usedWikiBefore?" to usedWikiBefore,
                    "textToSearch" to searchText
            )

            // Handle unexpected text here?
            
            db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.i("See Data", "${document.id} => ${document.data}")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.i("No data", "Error getting documents.", exception)
                    }

            db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d("No Error", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error adding document", e)
                    }

            findNavController().navigate(R.id.action_formFragment_to_displayFragment)
        }

    }
}