package com.example.debora.requisicaovolley

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : Activity (){


    var cep: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val txtCep : TextView = findViewById(R.id.cep) as EditText

        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener {
            apiHit(txtCep.text.toString())
        }

    }

    private fun apiHit(cep : String) {
        val bairro : TextView = findViewById(R.id.bairro)
        val cidade : TextView = findViewById(R.id.cidade)
        val logradouro : TextView = findViewById(R.id.logradouro)
        val estado : TextView = findViewById(R.id.estado)
        val nome : TextView = findViewById(R.id.nome)

        val url = "http://api.postmon.com.br/v1/cep/"+cep
        val queue : RequestQueue = Volley.newRequestQueue(this)
        val request =  JsonObjectRequest(
                Request.Method.GET ,
                url,
                null ,
                {
                    response: JSONObject? ->
                    if(response != null) {
                        bairro.text = "Bairro: "+response.getString("bairro")
                        cidade.text = "Cidade: "+response.getString("cidade")
                        logradouro.text = "Logradouro: "+response.getString("logradouro")
                        estado.text = "Estado: "+response.getString("estado")

                    }
                } ,
                {
                    error: VolleyError? ->
                    bairro.text =  error.toString()
                }
        )
        queue.add(request)
    }
}
