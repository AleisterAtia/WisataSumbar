package com.fadhiil2010.mapskotlin.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadhiil2010.mapskotlin.Detail
import com.fadhiil2010.mapskotlin.Model.ItemWisata
import com.fadhiil2010.mapskotlin.R

class ItemAdapter(
    private val itemOrder: List<ItemWisata>) :
    RecyclerView.Adapter<ItemAdapter.BukuAdapterHolder>() {

    //view holder yang digunakan unutk menyimpan referensi layout item
    class BukuAdapterHolder(view: View) : RecyclerView.ViewHolder(view){
        val imgMenu: ImageView = itemView.findViewById(R.id.imgWisata)
        val txtNamaMenu: TextView = itemView.findViewById(R.id.tvNamaWisata)
        val txtItems: TextView = itemView.findViewById(R.id.tvLokasi)
    }

    //pengaturan data item pada setiap list dari recycle view
    override fun onBindViewHolder(holder: BukuAdapterHolder, position: Int) {
        val currentItem = itemOrder[position]
        holder.imgMenu.setImageResource(currentItem.gambarWisata)
        holder.txtNamaMenu.setText(currentItem.namaWisata)
        holder.txtItems.setText(currentItem.lokasi)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, Detail::class.java).apply {
                // Mengirim data melalui intent
                putExtra("namaWisata", currentItem.namaWisata)
                putExtra("photoWisata", currentItem.gambarWisata)
                putExtra("sinopsis", currentItem.sinopsis)
                putExtra("lat", currentItem.lat)
                putExtra("long", currentItem.long)


            }
            context.startActivity(intent)
        }

    }

    //membuat layout list item sebagai item dari recycle view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuAdapterHolder {

        // Inflate layout item_destination.xml (pastikan file XML ini ada)
        val nView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return BukuAdapterHolder(nView)
    }

    //mengembalikan panjang nilai dari data
    override fun getItemCount(): Int{
        return itemOrder.size
    }

}