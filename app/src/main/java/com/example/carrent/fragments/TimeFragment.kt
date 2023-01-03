package com.example.carrent.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carrent.R

class TimeFragment : Fragment(R.layout.fragment_time) {

    private lateinit var spot: TextView
    private lateinit var photo: ImageView
    private lateinit var plusText: TextView
    private lateinit var minusText: TextView
    private lateinit var timeText: TextView
    private lateinit var costText: TextView
    private lateinit var timeBookButton: Button
    private var timeNumberResult = 0
    private var costNumberResult = 0
    private var number="0"



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        plusText = view.findViewById(R.id.plus)
        minusText = view.findViewById(R.id.minus)
        timeText = view.findViewById(R.id.timenumber)
        costText = view.findViewById(R.id.pricenumber)
        timeBookButton = view.findViewById(R.id.book)
        spot = view.findViewById(R.id.spot)
        photo = view.findViewById(R.id.photo)

        number = TimeFragmentArgs.fromBundle(requireArguments()).num

        if (number == "1") {
            spot.text = TimeFragmentArgs.fromBundle(requireArguments()).spotNameResult
            photo.setImageResource(TimeFragmentArgs.fromBundle(requireArguments()).spotImage)
        }

        plusText.setOnClickListener {
            timeNumberResult += 1
            timeText.text = timeNumberResult.toString()
            costNumberResult += 2
            costText.text = costNumberResult.toString()

        }


        minusText.setOnClickListener {
            if (timeNumberResult != 0) {
                timeNumberResult -= 1
                timeText.text = timeNumberResult.toString()
                costNumberResult -= 2
                costText.text = costNumberResult.toString()
            }

        }



        timeBookButton.setOnClickListener {

            if (number =="0") {
                val action = TimeFragmentDirections.actionTimeFragmentToRentalFragment()
                findNavController().navigate(action)
            }

        }


    }

}