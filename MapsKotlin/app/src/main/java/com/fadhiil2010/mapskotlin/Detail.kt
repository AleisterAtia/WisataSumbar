package com.fadhiil2010.mapskotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fadhiil2010.mapskotlin.databinding.ActivityMaps2Binding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Detail : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_wisata) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val photo = intent.getIntExtra("photoWisata", 0)
        val bidang = intent.getStringExtra("ketWisata")
        val nama = intent.getStringExtra("namaWisata")
        val sinopsis = intent.getStringExtra("sinopsis")
        val lat = intent.getDoubleExtra("lat",0.0)
        val long = intent.getDoubleExtra("long",0.0)



        val imgphoto = findViewById<ImageView>(R.id.imgDetail)
        val txtdetail = findViewById<TextView>(R.id.tvDetail)
        val textViewDesc = findViewById<TextView>(R.id.tvDetail)
        val txtNama = findViewById<TextView>(R.id.tvNama)


        txtdetail.text = bidang
        txtNama.text = nama
        imgphoto.setImageResource(photo)
        textViewDesc.setText(sinopsis)

        val ActButton2 = findViewById<Button>(R.id.btnMap)
        ActButton2.setOnClickListener {
            val Intent = Intent(this, MapsActivity2::class.java).apply {
                putExtra("lat", lat)
                putExtra("long", long)
                putExtra("namaWisata", nama)

            }
            startActivity(Intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val lat = intent.getDoubleExtra("lat",0.0)
        val lng = intent.getDoubleExtra("long",0.0)
        val lokasiwisata= LatLng(lat,lng)
        val namawisata = intent.getStringExtra("namaWisata")

        mMap.addMarker(MarkerOptions().position(lokasiwisata).title(namawisata))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasiwisata,15f))
    }
}