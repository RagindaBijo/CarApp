package com.example.carrent.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.carrent.ForgotPassword
import com.example.carrent.LogIn
import com.example.carrent.PersonInfo
import com.example.carrent.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("Users")

    private lateinit var displayName: TextView
    private lateinit var displaySurName: TextView
    private lateinit var displayID: TextView
    private lateinit var displayPhone: TextView
    private lateinit var logOutBtn: Button
    private lateinit var changePassword: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        logOutBtn = view.findViewById(R.id.logout)
        changePassword = view.findViewById(R.id.changePassword)
        displayName = view.findViewById(R.id.name)
        displaySurName = view.findViewById(R.id.surName)
        displayID = view.findViewById(R.id.id)
        displayPhone = view.findViewById(R.id.phoneNumber)


        db.child(auth.currentUser?.uid!!).get().addOnSuccessListener {

            if (it.exists()) {

                val name = it.child("name").value
                val surName  = it.child("surName").value
                val personalID = it.child("personalID").value
                val phoneNumber  = it.child("phoneNumber").value

                displayName.text="სახელი: "+name.toString()
                displaySurName.text="გვარი: "+surName.toString()
                displayID.text="ID: "+personalID.toString()
                displayPhone.text="ტელ:"+phoneNumber.toString()

            }else {
                Toast.makeText(this.requireContext(), "მომხმარებელი არ არებობს!", Toast.LENGTH_SHORT).show()
            }


        }.addOnFailureListener {
            Toast.makeText(this.requireContext(), "ხარვეზი!", Toast.LENGTH_SHORT).show()

        }



        logOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this.requireContext(), LogIn::class.java)
            startActivity(intent)
        }



        changePassword.setOnClickListener {
            val forget=Intent(this.requireContext(), ForgotPassword::class.java)
            startActivity(forget)
        }
    }


}

