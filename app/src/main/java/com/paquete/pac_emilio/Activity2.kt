package com.paquete.pac_emilio

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.paquete.pac_emilio.databinding.Activity2Binding
import java.io.File
import java.io.IOException
import java.util.*


class Activity2 : AppCompatActivity() {

    private lateinit var binding: Activity2Binding
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath: String

    /**
     * La Activity2 es la cámara de fotos
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Cámara"
        setContentView(R.layout.activity_2)

        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        escuchaTodo()
    }

    private fun escuchaTodo() {

        // Listener del botón de regreso
        binding.buttonVolver.setOnClickListener {
            finish()
        }

        // Listener para realizar foto
        binding.buttonFoto.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    /**
     *  Funciones que realizan la fotografía
     */

    @SuppressLint("QueryPermissionsNeeded")
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                binding.miniatura.setImageBitmap(imageBitmap)
            }
        }

        @Throws(IOException::class)
        private fun createImageFile(): File {
            // Create an image file name
            val timeStamp: SimpleDateFormat = SimpleDateFormat("MMdd_HHmmss", Locale.US)
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
                // Save a file: path for use with ACTION_VIEW intents
                currentPhotoPath = absolutePath
            }
        }
}



