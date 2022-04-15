package com.paquete.pac_emilio

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.paquete.pac_emilio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /**
     * Este es el Main de la aplicación, realizada para la asignatura "Programación multimedia
     * y dispositivos móviles", del Ciclo Superior de DAM, duarante el curso 2021/22.
     */

    private lateinit var mp : MediaPlayer // Reproductor de audio
    private lateinit var binding: ActivityMainBinding // Enlazador con la interfaz
    private val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Pack Multimedia"
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenerReproductor()
        accesoActivities()
        camaraActivity()
    }

    /**
     *  AUDIO; respuestas de presionar cada botón
     */
    private fun listenerReproductor() {

        mp = MediaPlayer.create(this, R.raw.churrita)

        mp.setOnCompletionListener {
            stopAudio()
        }
        binding.buttonPlay.setOnClickListener {
            if (mp.isPlaying) {
                pauseAudio()
                binding.buttonPlay.text = ">"
            } else {
                playAudio()
            }
        }
        binding.buttonStop.setOnClickListener {
            stopAudio()
        }
    }

    /**
     *  Acceso al resto de activities y permisos para su uso
     */
    private fun camaraActivity(){

        binding.buttonAct3.setOnClickListener {

            // Aquí falta congifurar un "onrequestpermissionresult"
            if (ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Acepte los permisos y vuelva a pinchar en Cámara",
                    Toast.LENGTH_LONG).apply {setGravity(0, 0, 0); show() }

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.CAMERA), WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
            }
                if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                        this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                } else {

                    val intent = Intent(this, Activity2::class.java)
                    startActivity(intent)
                }
        }
    }

    /**
     * Acceso al resto de activities
     */
    private fun accesoActivities() {

        binding.buttonAct4.setOnClickListener {

            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }

        binding.buttonAct2.setOnClickListener {

            val intent = Intent(this, Activity1::class.java)
            startActivity(intent)
        }
    }

    /**
     *  Botones del reproductor de audio
    */
    private fun playAudio(){
        mp.start()
        binding.buttonStop.isEnabled=true
        binding.buttonPlay.text="="
    }

    private fun pauseAudio(){
        mp.pause()
    }

    private fun stopAudio(){
        mp.stop()
        mp.prepare()
        mp.seekTo(0)
        binding.buttonStop.isEnabled=false
        binding.buttonPlay.text = ">"
    }
}