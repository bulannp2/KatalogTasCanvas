package com.example.katalogtascanvas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.katalogtascanvas.database.AppDatabase;
import com.example.katalogtascanvas.database.entitas.Tas;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnTambah;
    int idTas;
    private RecyclerView rvIndex;
    private AppDatabase database;
    List<Tas> dataTas = new ArrayList<>();
    private TasAdapter tasAdapter;
    private List<Tas> list = new ArrayList<>();
    private AlertDialog.Builder dialog;
    String nama, warna, harga, bahan, ukuran, stok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvIndex = findViewById(R.id.rv_tas);

        btnTambah = (Button) findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getApplicationContext(), ActivityTambah.class);
                startActivity(intent);
            }
        });

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.tassDao().getAll());
        tasAdapter = new TasAdapter(getApplicationContext(),list);
//        tasAdapter.setDialog(new TasAdapter.Dialog() {
//            @Override
//            public void onClick(int position) {
//                final CharSequence[] dialogItem = {"Detail Produk", "Edit Produk", "Hapus Produk"};
//                dialog = new AlertDialog.Builder(MainActivity.this);
//                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        switch (i){
//                            case 0:
//
////                                Intent detail = new Intent(MainActivity.this, ActivityTampil.class);
////                                detail.putExtra("id_tas",idTas);
//                                Toast.makeText(MainActivity.this, " " + idTas, Toast.LENGTH_SHORT).show();
////                                startActivity(detail);
//                                break;
//                            case 1:
//                                Intent intent = new Intent(MainActivity.this, ActivityTambah.class);
//                                intent.putExtra("id_tas", list.get(position).id_tas);
//                                startActivity(intent);
//                                break;
//                            case 2:
//                                Tas tas = list.get(position);
//                                database.tassDao().delete(tas);
//                                onStart();
//                                break;
//                        }
//                    }
//                });
//                dialog.show();
//
//            }
//        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        rvIndex.setLayoutManager(layoutManager);
        rvIndex.setAdapter(tasAdapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityTambah.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.tassDao().getAll());
        tasAdapter.notifyDataSetChanged();
    }

}