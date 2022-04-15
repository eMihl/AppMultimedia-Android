package com.paquete.pac_emilio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paquete.pac_emilio.databinding.Activity3Binding

class Activity3 : AppCompatActivity() {

    /**
     * La Activity3 es el collage de imágenes
     */

    private lateinit var binding: Activity3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Collage"
        setContentView(R.layout.activity_3)

        binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        botonVolver();
    }

    private fun botonVolver(){

        binding.buttonVolver3.setOnClickListener {
            finish()
        }
    }
}