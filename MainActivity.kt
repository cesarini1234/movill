package com.example.chichin

import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private lateinit var edId: EditText
    private lateinit var edNombre: EditText
    private lateinit var edDescripcion: EditText
    private lateinit var edPrecio: EditText

    private lateinit var edImagen: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnBuscar: Button
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edId = findViewById(R.id.edId)
        edNombre = findViewById(R.id.edNombre)
        edDescripcion = findViewById(R.id.edDescripcion)
        edPrecio = findViewById(R.id.edPrecio)

        edImagen = findViewById(R.id.edImagen)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnBuscar = findViewById(R.id.btnBuscar)
        btnEditar = findViewById(R.id.btnEditar)
        btnEliminar = findViewById(R.id.btnEliminar)

        btnAgregar.setOnClickListener {
            ejecutarServicio("http://25.13.17.190/insertar_producto.php")
        }
    }

    private fun ejecutarServicio(url: String) {
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(applicationContext, "OPERACION EXITOSA", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = HashMap()
                parametros["id"] = edId.text.toString()
                parametros["nombre"] = edNombre.text.toString()
                parametros["descripcion"] = edDescripcion.text.toString()
                parametros["precio"] = edPrecio.text.toString()
                parametros["imagen_url"] = edImagen.text.toString()
                return parametros
            }
        }
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}