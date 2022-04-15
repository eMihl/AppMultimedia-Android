package com.paquete.pac_emilio

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pac_emilio.AdminSQLiteOpenHelper
import kotlinx.android.synthetic.main.activity_1.*

class Activity1 : AppCompatActivity() {

    /**
     * La Activity1 es la base de datos
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        Toast.makeText(this, "Ha entrado en la Base de Datos",
            Toast.LENGTH_SHORT).show()

        botonVolver()
        botonesDatabase()
    }

    private fun botonesDatabase(){

        // botonInsertarUser
        botonInsertarUser.setOnClickListener {

            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()

            registro.put("id", et1.getText().toString())
            registro.put("nombre", et2.getText().toString())
            registro.put("categoria", et3.getText().toString())
            bd.insert("usuarios", null, registro)
            bd.close()
            et1.setText("")
            et2.setText("")
            et3.setText("")
            Toast.makeText(this, "Se suscribió un nuevo cliente", Toast.LENGTH_SHORT).show()
        }

        // botonConsultarUser
        botonConsultarUser.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery(
                "select nombre,categoria from usuarios where id=${et1.text.toString()}",
                null)

            if (fila.moveToFirst()) {

                et2.setText(fila.getString(0))
                et3.setText(fila.getString(1))
                bd.close()
            } else{
                Toast.makeText(this, "No existe un usuario con dicha ID", Toast.LENGTH_SHORT).show()
                bd.close()
            }
        }

        // botonEliminarUser
        botonEliminarUser.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val cant = bd.delete("usuarios", "id=${et1.text.toString()}", null)
            bd.close()
            et1.setText("")
            et2.setText("")
            et3.setText("")
            if (cant == 1)
                Toast.makeText(this, "Se borró el usuario", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No existe un usuario con ese ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun botonVolver() {

        buttonVolver.setOnClickListener {
            finish()
        }
    }
}