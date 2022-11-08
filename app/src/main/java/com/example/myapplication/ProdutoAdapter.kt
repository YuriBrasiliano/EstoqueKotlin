package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class ProdutoAdapter(private val context: Context, private val produtos: MutableList<Produto>): RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false)
        val holder = ProdutoViewHolder(itemLista)
        return holder
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.foto.setImageResource(produtos[position].foto)
        holder.nomeProduto.text = produtos[position].nome
        holder.descProduto.text = "\nRua: "+produtos[position].rua +"       Numero: " +produtos[position].numero + "        Andar: " +produtos[position].andar
        holder.quantProduto.text = "Quantidade: " +produtos[position].quantidade.toString()
    }

    override fun getItemCount(): Int = produtos.size
    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foto = itemView.findViewById<ImageView>(R.id.ftProduto)
        val nomeProduto = itemView.findViewById<TextView>(R.id.nomeProduto)
        val descProduto = itemView.findViewById<TextView>(R.id.descProduto)
        val quantProduto = itemView.findViewById<TextView>(R.id.quantProduto)
    }

}