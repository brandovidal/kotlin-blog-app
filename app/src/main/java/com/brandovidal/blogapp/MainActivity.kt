package com.brandovidal.blogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        // Get info
        db.collection("cities").document("LA").addSnapshotListener { value, error ->
            error?.let {
                Log.e("FirebaseError", error.toString())
                    return@addSnapshotListener
            }

            value?.let { document ->
                val city = document.toObject(City::class.java)
                Log.d("Firebase", "DocumentSnapshot color: ${city?.color}")
                Log.d("Firebase", "DocumentSnapshot population: ${city?.population}")
                Log.d("Firebase", "DocumentSnapshot pc: ${city?.pc}")

            }
        }

        // Insert info
        db.collection("cities").document("NY").set(City(52000, "Yellow"))
            .addOnSuccessListener {
                Log.d("Firebase", "Save the info")
            }.addOnFailureListener { error ->
                Log.e("FirebaseError", error.toString())
            }

    }
}

data class City(val population: Int = 0, val color: String = "", val pc: Int = 0)