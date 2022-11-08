package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    private val obterResultado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        intentResult.contents.replace("\\s".toRegex(), "")

        val builder = AlertDialog.Builder(this)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("OK"){dialogInterface, which ->}

        val cods : List<String> = listOf("7894900010015", "7894900011517", "7891991000833","7891991011020","7898712836870","7894900039924","7894900031201","7892840800079",
            "7892840813017", "7896004000855", "7896004003979", "7896110005140", "7896104998953", "7896076002146","7896276060021", "7898295150189",
            "7896086423030", "7896864400192", "7897924800877", "7898084090030","7891959004415","7896032501010", "7896109801005", "7896319420546",
            "7896089028935", "7898286200077","7891910010905", "7898079250012","7891107000504", "7896334200550","7896036090107")
        val produtosParaSeparacao: ArrayList<String> = ArrayList()
        val produtosErrados: ArrayList<String> = ArrayList()
        if (intentResult.contents != null) {
            val lstProdutos: List<String> = intentResult.contents.split("|").map { it -> it.trim() }
            lstProdutos.forEach { it ->
                val prodquant: List<String> = it.split(":" )
                val prod = prodquant[0]
                if (cods.contains(prod)) {
                    produtosParaSeparacao.add(it)
                }else{
                    produtosErrados.add(prod)
               }
            }
            if(produtosParaSeparacao.isNotEmpty()){
                val separacao = Intent(this, Separacao::class.java)
                separacao.putExtra("ProdutosParaSeparar", produtosParaSeparacao)
                    .putExtra("ProdutosErrados", produtosErrados)
                startActivity(separacao)
            }else{
                if(produtosErrados.isNotEmpty()){
                    builder.setTitle("Produto inválido!")
                    builder.setMessage("Os Produtos com código \n\n$produtosErrados \n\nQue você procura não foram encontrados. \n\nPor favor tente novamente ou entre em contato com o administrador")
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
            }
        }else{
            builder.setTitle("QR Code sem valor")
            builder.setMessage("O QRCode tem valor nulo!")
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            Toast.makeText(applicationContext,"O QRCode tem valor nulo!",Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLerQrCode: Button = findViewById(R.id.btnLerQrCode)
        btnLerQrCode.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultado.launch(integrator.createScanIntent())
        }
        val btnFechar: Button = findViewById(R.id.btnFechar)
        btnFechar.setOnClickListener {
            finish()
        }

    }
    override fun onStart() {
        super.onStart()
    }
    override fun onRestart() {
        super.onRestart()
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
    }
    override fun onStop() {
        super.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}


