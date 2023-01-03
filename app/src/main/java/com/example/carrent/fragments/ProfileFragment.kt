package com.example.carrent.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.carrent.LogIn
import com.example.carrent.PersonInfo
import com.example.carrent.R
import com.example.carrent.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFragment:Fragment(R.layout.fragment_profile) {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("USER_INFO")

    private lateinit var displayName:TextView
    private lateinit var displaySurName:TextView
    private lateinit var displayID:TextView
    private lateinit var displayPhone:TextView
    private lateinit var logOutBtn:Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        logOutBtn=view.findViewById(R.id.logout)
        displayName=view.findViewById(R.id.name)
        displaySurName=view.findViewById(R.id.surName)
        displayID=view.findViewById(R.id.id)
        displayName=view.findViewById(R.id.phoneNumber)


        db.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(PersonInfo::class.java) ?: return

                displayName.text=userInfo.name
                displaySurName.text=userInfo.surName
                displayID.text=userInfo.personalID
                displayPhone.text=userInfo.phoneNumber


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })





        logOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this.requireContext(),LogIn::class.java)
            startActivity(intent)
        }






    }



}