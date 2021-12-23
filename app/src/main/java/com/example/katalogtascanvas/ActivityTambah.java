package com.example.katalogtascanvas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katalogtascanvas.database.AppDatabase;
import com.example.katalogtascanvas.database.entitas.Tas;

import java.util.ArrayList;
import java.util.List;

public class ActivityTambah extends AppCompatActivity implements View.OnClickListener {

    EditText editNama, editBahan, editHarga;
    SeekBar seekbarStok;
    TextView stokSeekbar;
    RadioButton radioMini, radioMedium, rb;
    RadioGroup rgSize;
    Button btnTampil;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    String warna = "";
    List<Tas> dataTas = new ArrayList<>();


    private AppDatabase database;
    private boolean isEdit = false;
    private int id_tas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        database = AppDatabase.getInstance(getApplicationContext());


        editNama = (EditText) findViewById(R.id.editNama);
        editBahan = (EditText) findViewById(R.id.editBahan);
        editHarga = (EditText) findViewById(R.id.editHarga);

        radioMini = (RadioButton) findViewById(R.id.radioMini);
        radioMedium = (RadioButton) findViewById(R.id.radioMedium);
        rgSize = (RadioGroup) findViewById(R.id.rGroup_Ukuran);

        cb1 = (CheckBox) findViewById(R.id.warna1);
        cb2 = (CheckBox) findViewById(R.id.warna2);
        cb3 = (CheckBox) findViewById(R.id.warna3);
        cb4 = (CheckBox) findViewById(R.id.warna4);
        cb5 = (CheckBox) findViewById(R.id.warna5);
        cb6 = (CheckBox) findViewById(R.id.warna6);


        btnTampil = (Button) findViewById(R.id.btnTampilkan);

        btnTampil.setOnClickListener(this);

        seekbarStok = (SeekBar) findViewById(R.id.seekbarStok);
        stokSeekbar = (TextView) findViewById(R.id.stokSeekbar);


        Intent intent = getIntent();
        id_tas = intent.getIntExtra("id_tas", 0);
        Toast.makeText(ActivityTambah.this, "" + String.valueOf(id_tas), Toast.LENGTH_SHORT).show();
        if (id_tas>0) {
            isEdit = true;
            Tas tas = database.tassDao().get(id_tas);
            editNama.setText(tas.nama);
            editBahan.setText(tas.bahan);
            editHarga.setText(tas.harga);
            stokSeekbar.setText(tas.stok);

            if (tas.ukuran.toString().equals("Mini")) {
                radioMini.setChecked(true);
            } else if (tas.ukuran.toString().equals("Medium")) {
                radioMedium.setChecked(true);
            }

        }else{
            isEdit = false;
        }

        seekbarStok.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stokSeekbar.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        int radio = rgSize.getCheckedRadioButtonId();
        rb = findViewById(radio);

        String Nama = editNama.getText().toString();
        String Bahan = editBahan.getText().toString();
        String Harga = editHarga.getText().toString();
        String stok = stokSeekbar.getText().toString();
        String size = rb.getText().toString();


        //Check Box
        if (cb1.isChecked()) {
            warna += "Pink";
        }
        if (cb2.isChecked()) {
            warna += "Hitam";
        }
        if (cb3.isChecked()) {
            warna += "Forest Green";
        }
        if (cb4.isChecked()) {
            warna += "Putih";
        }
        if (cb5.isChecked()) {
            warna += "Milo";
        }
        if (cb6.isChecked()) {
            warna += "Navy";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Toast.makeText(this, "Mohon Cek Kembali", Toast.LENGTH_SHORT).show();
        builder.setTitle("Pengecekan Ulang");
        builder.setMessage(
                        "Barang yang anda tambahkan adalah " + String.valueOf(Nama) +
                                " " + String.valueOf(size) +
                                " " + String.valueOf(warna) +
                                " bahan " + String.valueOf(Bahan) +
                                " seharga " + "Rp." + String.valueOf(Harga) +
                                " dengan jumlah stok " + String.valueOf(stok) + "." + "\n" + "\n" + "Apakah anda yakin ingin menyimpan produk ini ?")
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        if (isEdit){
                            database.tassDao().update(id_tas, Nama, warna, Harga,Bahan,size,stok);
                            intent.putExtra("nama", Nama);
                            intent.putExtra("warna", warna.toString());
                            intent.putExtra("harga", Harga);
                            intent.putExtra("bahan", Bahan);
                            intent.putExtra("ukuran", size);
                            intent.putExtra("stok", stok);
                        }else{
                            database.tassDao().insertTas(Nama, warna, Harga,Bahan,size,stok);
                            intent.putExtra("nama", Nama);
                            intent.putExtra("warna", warna.toString());
                            intent.putExtra("harga", Harga);
                            intent.putExtra("bahan", Bahan);
                            intent.putExtra("ukuran", size);
                            intent.putExtra("stok", stok);
                        }

                        startActivity(intent);
                        finish();
                    }

                });
        builder.setNegativeButton(
                "Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityTambah.this, "Mohon input dengan benar", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialoghasil = builder.create();
        dialoghasil.show();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    };




    //Untuk menampilkan hasil input data diatas di Activity Tampil
    public void openActivityTampil() {
        Intent intent = new Intent(this, ActivityTampil.class);

        String Nama = editNama.getText().toString();
        String Bahan = editBahan.getText().toString();
        String Harga = editHarga.getText().toString();
        String size = "";
        String warna = "";
        String stok = stokSeekbar.getText().toString();


        //Radio Button Size
        if (radioMini.isChecked()) {
            size += "Mini";
        }
        if (radioMedium.isChecked()) {
            size += "Medium";
        }


        //Check Box
        if (cb1.isChecked()) {
            warna += "Pink";
        }
        if (cb2.isChecked()) {
            warna += "Hitam";
        }
        if (cb3.isChecked()) {
            warna += "Forest Green";
        }
        if (cb4.isChecked()) {
            warna += "Putih";
        }
        if (cb5.isChecked()) {
            warna += "Milo";
        }
        if (cb6.isChecked()) {
            warna += "Navy";
        }

        intent.putExtra("nama", Nama);
        intent.putExtra("warna", warna);
        intent.putExtra("harga", Harga);
        intent.putExtra("bahan", Bahan);
        intent.putExtra("ukuran", size);
        intent.putExtra("stok", stok);

        startActivity(intent);

    }

    //Lifecycle
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Silahkan Masukan Data Produk", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Input Data Sedang Berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Mohon Menunggu",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Produk Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Aplikasi ditutup, Selamat Tinggal",Toast.LENGTH_SHORT).show();
    }

}