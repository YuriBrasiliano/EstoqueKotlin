package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Separacao : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_separacao)

        val produtos : HashMap<String,Produto> = HashMap<String, Produto>()
        produtos.put("7894900010015", Produto("REFRIGERANTE COCA-COLA LATA 350ML","A", "1", "1", 1000, R.drawable.p7894900010015))
        produtos.put("7894900011517", Produto("REFRIGERANTE COCA-COLA GARRAFA 2L","A", "1", "2", 1000, R.drawable.p7894900011517))
        produtos.put("7891991000833", Produto("REFRIGERANTE SODA LIMONADA ANTARTIC LATA 350ML","A", "1", "3", 1000, R.drawable.p7891991000833))
        produtos.put("7891991011020", Produto("REFRIGERANTE GUARANA ANTARCTICA LATA 350ML","A", "2", "1", 1000, R.drawable.p7891991011020))
        produtos.put("7898712836870", Produto("REFRIGERANTE GUARANA ANTARCTICA 2L","A", "2", "2", 1000, R.drawable.p7898712836870))
        produtos.put("7894900039924", Produto("REFRIGERANTE FANTA LARANJA 2L","A", "2", "3", 1000, R.drawable.p7894900039924))
        produtos.put("7894900031201", Produto("REFRIGERANTE FANTA LARANJA LATA 350ML","A", "2", "4", 1000, R.drawable.p7894900031201))
        produtos.put("7892840800079", Produto("REFRIGERANTE PEPSI LATA 350ML","A", "3", "1", 1000, R.drawable.p7892840800079))
        produtos.put("7892840813017", Produto("REFRIGERANTE PEPSI 2L","A", "3", "2", 1000, R.drawable.p7892840813017))
        produtos.put("7896004000855", Produto("SUCRILHOS KELLOGG'S ORIGINAL 250G","B", "1", "1", 1000, R.drawable.p7896004000855))
        produtos.put("7896004003979", Produto("SUCRILHOS KELLOGG'S CHOCOLATE 320G","B", "1", "2", 1000, R.drawable.p7896004003979))
        produtos.put("7896110005140", Produto("PAPEL HIGIÊNICO PERSONAL FOLHA SIMPLES NEUTRO 60 METROS 4 UNIDADES","B", "2", "1", 1000, R.drawable.p7896110005140))
        produtos.put("7896104998953", Produto("PAPEL HIGIÊNICO MILI 4R","B", "2", "2", 1000, R.drawable.p7896104998953))
        produtos.put("7896076002146", Produto("PAPEL HIGIENICO DAMA 60MTR","B", "2", "3", 1000, R.drawable.p7896076002146))
        produtos.put("7896276060021", Produto("ARROZ AGULHINHA ARROZAL T1 5KG","C", "1", "1", 1000, R.drawable.p7896276060021))
        produtos.put("7898295150189", Produto("ARROZ SABOROSO 5KG","C", "1", "2", 1000, R.drawable.p7898295150189))
        produtos.put("7896086423030", Produto("ARROZ TRIMAIS 5KG","C", "1", "3", 1000, R.drawable.p7896086423030))
        produtos.put("7896864400192", Produto("FEIJAO PICININ 1KG","C", "2", "1", 1000, R.drawable.p7896864400192))
        produtos.put("7897924800877", Produto("FEIJAO PRETO VENEZA 1KG","C", "2", "2", 1000, R.drawable.p7897924800877))
        produtos.put("7898084090030", Produto("FEIJÃO PEREIRA CARIOQUINHA 1KG","C", "2", "3", 1000, R.drawable.p7898084090030))
        produtos.put("7891959004415", Produto("AÇUCAR REFINADO DOÇULA 1KG","D", "1", "1", 1000, R.drawable.p7891959004415))
        produtos.put("7896032501010", Produto("AÇÚCAR REFINADO DA BARRA 1KG","D", "1", "2", 1000, R.drawable.p7896032501010))
        produtos.put("7896109801005", Produto("AÇÚCAR REFINADO ESPECIAL GUARANI 1KG","D", "1", "3", 1000, R.drawable.p7896109801005))
        produtos.put("7896319420546", Produto("ACUCAR REFINADO CLARION 1KG","D", "2", "1", 1000, R.drawable.p7896319420546))
        produtos.put("7896089028935", Produto("CAFÉ TORRADO MOÍDO POUCHE CAFÉ DO PONTO 500G","D", "2", "2", 1000, R.drawable.p7896089028935))
        produtos.put("7898286200077", Produto("CAFE MARATA 500G","D", "2", "3", 1000, R.drawable.p7898286200077))
        produtos.put("7891910010905", Produto("CAFE CABOCLO 500G","D", "3", "1", 1000, R.drawable.p7891910010905))
        produtos.put("7898079250012", Produto("CAFE FIORENZA 500G","D", "3", "2", 1000, R.drawable.p7898079250012))
        produtos.put("7891107000504", Produto("OLEO DE SOJA SOYA 1L","E", "1", "1", 1000, R.drawable.p7891107000504))
        produtos.put("7896334200550", Produto("OLEO DE SOJA GRANOL 1L","E", "2", "1", 1000, R.drawable.p7896334200550))
        produtos.put("7896036090107", Produto("OLEO DE SOJA VELEIRO 1L","E", "3", "1", 1000, R.drawable.p7896036090107))


        val recyclerProduto = findViewById<RecyclerView>(R.id.recyclerProdutos)
        recyclerProduto.layoutManager = LinearLayoutManager(this)
        recyclerProduto.setHasFixedSize(true)

        val listaProdutos: MutableList<Produto> = mutableListOf()
        val listaProdutosZerados: ArrayList<String> = ArrayList()
        val ProdutosParaRetirar = ArrayList<String>()
        val ProdutosParaRetirarEstoque = ArrayList<String>()
        val produtoAdapter = ProdutoAdapter(this,listaProdutos)
        recyclerProduto.adapter = produtoAdapter

        val ProdutosErrados : List<String>? = intent.getStringArrayListExtra("ProdutosErrados")
        if(ProdutosErrados!!.isNotEmpty()){
            errorMessage("Produto inválido!", "Os Produtos com código \n\n$ProdutosErrados \n\nQue você procura não foram encontrados.\n\nPor favor tente novamente ou entre em contato com o administrador!")
        }

        val ProdutosScanneados: List<String>? = intent.getStringArrayListExtra("ProdutosParaSeparar")
            ProdutosScanneados!!.forEach {
                val prodquant: List<String> = it.split(":")
                val prod = prodquant[0]
                val quant = prodquant[1].toInt()
                val produto = produtos.get(prod)
                ProdutosParaRetirarEstoque.add(produto!!.quantidade.toString())
                    if (produto!!.quantidade > quant && quant > 0) {
                            produto.quantidade =  quant
                            listaProdutos.add(produto)
                            ProdutosParaRetirar.add(produto.nome+"|"+produto.rua+"|"+produto.andar+"|"+produto.numero+"|"+produto.quantidade+"|"+produto.foto+"|"+prod)
                        }
                    else{
                        listaProdutosZerados.add(produto.nome)
                        errorMessage("Quantidade solicitada zerada ou maior do que estoque!", "Os Produtos \n\n $listaProdutosZerados \n\nQue você procura estão com a quantidade solicitada zerada ou em falta no estoque!\n\nPor favor entre em contato com o fornecedor!")
                }
            }

        val btnVoltar : Button = findViewById(R.id.btnVoltar)
        btnVoltar.setOnClickListener(View.OnClickListener {
            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)
            finish(); })
        val btnIniciar : Button = findViewById(R.id.btnIniciar)
        btnIniciar.setOnClickListener(View.OnClickListener {
            for (i in ProdutosParaRetirar) {
                val Finalizado = Intent(this, com.example.myapplication.Finalizado::class.java)
                Finalizado.putExtra("ProdutosRetirados", i)
                val quantidade  = "1000"
                Finalizado.putExtra("Quantidade Estoque", quantidade)
            startActivity(Finalizado)}
            for (i in ProdutosParaRetirar) {
                val LerQrCodeIntent = Intent(this, LerQrCode::class.java)
                LerQrCodeIntent.putExtra("ProdutosParaRetirar", i)
                startActivity(LerQrCodeIntent)
            }
            finish()

        })



        }
        fun errorMessage(title : String, message : String){
            val builder = AlertDialog.Builder(this)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("OK"){dialogInterface, which ->}
            builder.setTitle(title)
            builder.setMessage(message)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

        }
}




