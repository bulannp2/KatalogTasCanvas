package com.example.katalogtascanvas.database.entitas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tas {
    @PrimaryKey(autoGenerate = true)
    public int id_tas;

    public String nama;
    public String warna;
    public String harga;
    public String bahan;
    public String ukuran;
    public String stok;

    public int getId_peminjam() {
        return id_tas;
    }

    public void setId_peminjam(int id_peminjam) {
        this.id_tas = id_tas;
    }

    public String getNamaLengkap() {
        return nama;
    }

    public void setNamalengkap(String namalengkap) {
        this.nama = nama;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String namaHarga) {
        this.harga = harga;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getStock() {
        return stok;
    }

    public void setStock(String stock) {
        this.stok = stock;
    }
}
