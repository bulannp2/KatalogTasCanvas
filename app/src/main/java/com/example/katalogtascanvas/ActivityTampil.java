package com.example.katalogtascanvas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katalogtascanvas.database.AppDatabase;
import com.example.katalogtascanvas.database.entitas.Tas;

import java.util.ArrayList;
import java.util.List;

public class ActivityTampil extends AppCompatActivity {
    private AppDatabase database;
    List<Tas> dataTas = new ArrayList<>();
    int idTas;
    TextView tampilNama, tampilWarna, tampilHarga, tampilBahan, tampilUkuran, tampilStok;
    String nama, warna, harga, bahan, ukuran, stok;
    Button btnSubmit, btnHapus, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        tampilNama = (TextView) findViewById(R.id.tampilNama);
        tampilWarna = (TextView) findViewById(R.id.tampilWarna);
        tampilHarga = (TextView) findViewById(R.id.tampilHarga);
        tampilBahan = (TextView) findViewById(R.id.tampilBahan);
        tampilUkuran = (TextView) findViewById(R.id.tampilUkuran);
        tampilStok = (TextView) findViewById(R.id.tampilStok);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnHapus = (Button) findViewById(R.id.btnHapus);


        Intent tasIntent = getIntent();
        idTas = tasIntent.getIntExtra("id_tas", 0);
        Toast.makeText(this, "id_tas " + idTas, Toast.LENGTH_SHORT).show();
        if (idTas > 0) {
            showData();
        }



//        if (getIntent().getStringExtra("nama") != "") {
//            nama = getIntent().getStringExtra("nama");
//            tampilNama.setText(nama);
//        }
//        if (getIntent().getStringExtra("warna") != "") {
//            warna = getIntent().getStringExtra("warna");
//            tampilWarna.setText(warna);
//        }
//        if (getIntent().getStringExtra("harga") != "") {
//            harga = getIntent().getStringExtra("harga");
//            tampilHarga.setText(harga);
//        }
//        if (getIntent().getStringExtra("bahan") != "") {
//            bahan = getIntent().getStringExtra("bahan");
//            tampilBahan.setText(bahan);
//        }
//        if (getIntent().getStringExtra("ukuran") != "") {
//            ukuran = getIntent().getStringExtra("ukuran");
//            tampilUkuran.setText(ukuran);
//        }
//        if (getIntent().getStringExtra("stok") != "") {
//            stok = getIntent().getStringExtra("stok");
//            tampilStok.setText(stok);
//        }

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editIntent = new Intent(ActivityTampil.this, ActivityTambah.class);
//                editIntent.putExtra("id",idTas);
//                startActivity(editIntent);
//            }
//        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(ActivityTampil.this, ActivityTambah.class);
                editIntent.putExtra("id_tas",idTas);
                startActivity(editIntent);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ActivityTampil.this)
                        .setTitle("Hapus Produk")
                        .setMessage("Apakah anda yakin akan menghapus '"+nama+"'?")
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ActivityTampil.this,"Produk Terhapus!", Toast.LENGTH_SHORT).show();
                                database.tassDao().delete(idTas);
                                finish();
                            }
                        })
                        .setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            }
        });
    }
    public void  submit (View view) {
        Intent intent = new Intent(ActivityTampil.this, MainActivity.class);
        startActivity(intent);
    }

    public void showData() {
        database = AppDatabase.getInstance(ActivityTampil.this);
        dataTas = database.tassDao().selectTas(idTas);

        nama = dataTas.get(0).getNamaLengkap();
        warna = dataTas.get(0).getWarna();
        harga = dataTas.get(0).getHarga();
        bahan = dataTas.get(0).getBahan();
        ukuran = dataTas.get(0).getUkuran();
        stok = dataTas.get(0).getStock();

        tampilNama.setText(nama);
        tampilWarna.setText(warna);
        tampilHarga.setText(harga);
        tampilBahan.setText(bahan);
        tampilUkuran.setText(ukuran);
        tampilStok.setText(stok);

    }

}