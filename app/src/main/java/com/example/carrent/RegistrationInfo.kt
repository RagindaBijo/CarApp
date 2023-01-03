package com.example.carrent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carrent.databinding.ActivityRegistrationInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegistrationInfo : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("USER_INF")
    private lateinit var binding: ActivityRegistrationInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_info)

        supportActionBar?.hide()
        binding= ActivityRegistrationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstName=binding.regName
        val lastName=binding.regLastName
        val numID=binding.regId
        val numPhone=binding.regPhone
        val btnFinish=binding.finihBtn

        btnFinish.setOnClickListener {

            val name = firstName.text.toString()
            val surName = lastName.text.toString()
            val personalID = numID.text.toString()
            val phoneNumber = numPhone.text.toString()

            if (personalID.length<11||phoneNumber.length<9){
                Toast.makeText(this, "შეავსეთ სწორად!", Toast.LENGTH_SHORT).show()
            }else if (name.isEmpty()||surName.isEmpty()||personalID.isEmpty()||phoneNumber.isEmpty()){
                Toast.makeText(this, "გრაფა ცარიელია!", Toast.LENGTH_SHORT).show()
            }else{
                val personInfo = PersonInfo(name,surName,personalID,phoneNumber)
                db.child(personalID).setValue(personInfo).addOnSuccessListener {
                    Toast.makeText(this, "წარმატებით დარეგისტრირდით", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }

            }

        }


    }
}