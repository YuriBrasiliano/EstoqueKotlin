package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.integration.android.IntentIntegrator
import java.util.logging.Logger.getGlobal
import java.util.logging.Logger.global


class LerQrCode : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation", "ResourceAsColor")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ler_qr_code)
        val ProdutosParaRetirar : String? = intent.getStringExtra("ProdutosParaRetirar")


        val lstProdutos: List<String> = ProdutosParaRetirar!!.split("|").map { it -> it.trim() }
        val nome : String = lstProdutos[0]
        val rua : String = lstProdutos[1]
        val andar : String = lstProdutos[2]
        val numero : String = lstProdutos[3]
        val foto : Int = lstProdutos[5].toInt()
        var quantidade : Int = lstProdutos[4].toInt()

        val titleScanner : Unit = findViewById<TextView?>(R.id.titleScanner).setText("Por favor Scannear as seguintes informações do seguinte produto: \n\n"+nome)
        val labelScannerRua : Unit = findViewById<TextView?>(R.id.labelScannerRua).setText("Rua "+rua)
        val labelScannerNumero : Unit = findViewById<TextView?>(R.id.labelScannerNumero).setText("Numero "+numero)
        val labelScannerAndar : Unit = findViewById<TextView?>(R.id.labelScannerAndar).setText("Andar "+andar)
        val fotoProduto : Unit = findViewById<ImageView?>(R.id.fotoProduto).setImageResource(foto)

        val btnLerQrCodeRua: Button = findViewById(R.id.btnLerQrCodeRua)
        btnLerQrCodeRua.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultadoRua.launch(integrator.createScanIntent())
        }
        val btnLerQrCodeNumero: Button = findViewById(R.id.btnLerQrCodeNumero)
        btnLerQrCodeNumero.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultadoNumero.launch(integrator.createScanIntent())
        }
        val btnLerQrCodeAndar: Button = findViewById(R.id.btnLerQrCodeAndar)
        btnLerQrCodeAndar.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultadoAndar.launch(integrator.createScanIntent())
        }
        val btnLerQrCodeProdutos: Button = findViewById(R.id.btnLerQrCodeProdutos)
        btnLerQrCodeProdutos.setOnClickListener {
            val labelScannerProdutos : Unit = findViewById<TextView?>(R.id.labelScannerProdutos).setText(quantidade.toString() + " Produtos")
            if (quantidade>0){
                val integrator: IntentIntegrator = IntentIntegrator(this)
                integrator.setPrompt("Leitura de QRCode")
                obterResultadoProduto.launch(integrator.createScanIntent())
            quantidade--
            }else{
                val btnLerQrCodeProdutos: Button = findViewById(R.id.btnLerQrCodeProdutos)
                btnLerQrCodeProdutos.setText("Sucesso!")
                btnLerQrCodeProdutos.isEnabled = false
                btnLerQrCodeProdutos.setBackgroundColor(R.color.green)
                tracker()
            }
        }
        val labelScannerProdutos : Unit = findViewById<TextView?>(R.id.labelScannerProdutos).setText(quantidade.toString() + " Produtos")

        }

    fun lerQrCodeError() {
        val ProdutosParaRetirar : String? = intent.getStringExtra("ProdutosParaRetirar")
        val lstProdutos: List<String> = ProdutosParaRetirar!!.split("|").map { it -> it.trim() }
        val cod : String = lstProdutos[6]
        val builder = AlertDialog.Builder(this)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setTitle("Produto Inválido")
        builder.setMessage("Por favor se scannear o produto de código $cod")
        builder.setPositiveButton("OK") { dialogInterface, which ->
            val Finalizado = Intent(this, LerQrCode::class.java)
            Finalizado.putExtra("ProdutosParaRetirar", ProdutosParaRetirar)
            startActivity(Finalizado)
            finish()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

        @SuppressLint("ResourceAsColor")
    private val obterResultadoProduto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        intentResult.contents.replace("\\s".toRegex(), "")
        val ProdutosParaRetirar : String? = intent.getStringExtra("ProdutosParaRetirar")
        val lstProdutos: List<String> = ProdutosParaRetirar!!.split("|").map { it -> it.trim() }
        val cod : String = lstProdutos[6]
        if(intentResult.contents.contains(cod)){
        }else{
            lerQrCodeError()
        }
    }

    @SuppressLint("ResourceAsColor", "SuspiciousIndentation")
    private val obterResultadoRua = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        intentResult.contents.replace("\\s".toRegex(), "")
        val ProdutosParaRetirar : String? = intent.getStringExtra("ProdutosParaRetirar")
        val lstProdutos: List<String> = ProdutosParaRetirar!!.split("|").map { it -> it.trim() }
        val rua : String = lstProdutos[1]
            if(intentResult.contents.contains("R"+rua)){
                val btnLerQrCodeRua: Button = findViewById(R.id.btnLerQrCodeRua)
                btnLerQrCodeRua.setText("Sucesso!")
                btnLerQrCodeRua.isEnabled = false
                btnLerQrCodeRua.setBackgroundColor(R.color.green)
                tracker()
            }else{
                errorMessage("Local Inválido", "Por favor se dirija a \nRua $rua")
            }
        }

    @SuppressLint("ResourceAsColor", "SuspiciousIndentation")
    private val obterResultadoNumero = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        intentResult.contents.replace("\\s".toRegex(), "")
        val ProdutosParaRetirar : String? = intent.getStringExtra("ProdutosParaRetirar")
        val lstProdutos: List<String> = ProdutosParaRetirar!!.split("|").map { it -> it.trim() }
        val rua : String = lstProdutos[1]
        val numero : String = lstProdutos[3]
            if(intentResult.contents.contains("R"+rua) && intentResult.contents.contains("N"+numero)){
                val btnLerQrCodeNumero: Button = findViewById(R.id.btnLerQrCodeNumero)
                btnLerQrCodeNumero.setText("Sucesso!")
                btnLerQrCodeNumero.isEnabled = false
                btnLerQrCodeNumero.setBackgroundColor(R.color.green)
                tracker()
            }else{
                errorMessage("Local Inválido", "Por favor se dirija a \nRua $rua \nNúmero $numero")
            }
        }
    @SuppressLint("ResourceAsColor", "SuspiciousIndentation")
    private val obterResultadoAndar = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        intentResult.contents.replace("\\s".toRegex(), "")
        val ProdutosParaRetirar : String? = intent.getStringExtra("ProdutosParaRetirar")
        val lstProdutos: List<String> = ProdutosParaRetirar!!.split("|").map { it -> it.trim() }
        val andar : String = lstProdutos[2]
        val rua : String = lstProdutos[1]
        val numero : String = lstProdutos[3]
            if(intentResult.contents.contains(andar+"A") && intentResult.contents.contains("N"+numero) && intentResult.contents.contains("R"+rua)){
                val btnLerQrCodeAndar: Button = findViewById(R.id.btnLerQrCodeAndar)
                btnLerQrCodeAndar.setText("Sucesso!")
                btnLerQrCodeAndar.isEnabled = false
                btnLerQrCodeAndar.setBackgroundColor(R.color.green)
                tracker()
            }else{
                errorMessage("Local Inválido", "Por favor se dirija a \nRua $rua \nNúmero $numero \nAndar $andar")
            }
        }

    var trackfinishi : Int = 0
    fun errorMessage(title : String, message: String){
        val builder = AlertDialog.Builder(this)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("OK"){dialogInterface, which -> }
        builder.setTitle(title)
        builder.setMessage(message)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
}


    fun tracker(){
        trackfinishi ++
        if (trackfinishi == 4){
            val builder = AlertDialog.Builder(this)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("OK"){dialogInterface, which -> finish()}
            builder.setTitle("Sucesso!")
            builder.setMessage("Produto Retirado com Sucesso!")
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

}