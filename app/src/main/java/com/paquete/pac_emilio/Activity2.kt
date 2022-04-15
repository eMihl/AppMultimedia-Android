package com.paquete.pac_emilio

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

    /**La Activity2 es la cámara de fotos.
     * En este ejercicio he tenido un problema similar al de la base de datos. En este caso la
     * app consigue acceder a la cámara tras aceptar los permisos pertinentes, realizar la
     * fotografía y acceder a la miniatura, pero aún no está listo para que se almacene en el
     * dispositivo.
     * */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        escuchaTodo()
    }

    private fun escuchaTodo() {

        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonVolver.setOnClickListener {
            finish()
        }
        binding.buttonFoto.setOnClickListener {
            dispatchTakePictureIntent()

        }
    }

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



