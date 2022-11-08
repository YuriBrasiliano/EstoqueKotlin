package com.example.myapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Finalizado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalizado)

        val ProdutosParaRetirar : String? = intent.getStringExtra("ProdutosRetirados")

        val lstProdutos: List<String> = ProdutosParaRetirar!!.split("|").map { it -> it.trim() }
        val nome : String = lstProdutos[0]
        var quantidade : Int = lstProdutos[4].toInt()
        val foto : Int = lstProdutos[5].toInt()
        val cod : String = lstProdutos[6]

        val ProdutosParaRetirarEstoque : String? = intent.getStringExtra("Quantidade Estoque")

        val titleScanner : Unit = findViewById<TextView?>(R.id.titleScanner).setText("O seguinte produto foi retirado com sucesso!: \n\n\n\n"+nome+"\n\n\n\n Com o código $cod")
        val labelScannerRua : Unit = findViewById<TextView?>(R.id.labelScannerRua).setText("Quantidade ainda em estoque: \n" +(ProdutosParaRetirarEstoque!!.toInt()-quantidade))
        val fotoProduto : Unit = findViewById<ImageView?>(R.id.fotoProduto).setImageResource(foto)

        val btnVoltar : Button = findViewById(R.id.btnVoltar)
        btnVoltar.setOnClickListener(View.OnClickListener {

            finish() })

    }
}

