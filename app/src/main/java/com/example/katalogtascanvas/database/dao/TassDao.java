package com.example.katalogtascanvas.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.katalogtascanvas.database.entitas.Tas;

import java.util.List;

@Dao
public interface TassDao {
    @Query("SELECT * FROM tas ORDER BY id_tas DESC")
    List<Tas> getAll();

    @Query("INSERT INTO tas (nama, warna, harga, bahan, ukuran, stok) VALUES(:nama,:warna,:harga,:bahan,:ukuran,:stok)")
    void insertTas(String nama, String warna, String harga, String bahan, String ukuran, String stok);

    @Query("SElECT * FROM tas WHERE id_tas=:id_tas")
    List<Tas> selectTas(int id_tas);

    @Query("UPDATE tas SET nama=:nama , warna=:warna, harga=:harga , bahan=:bahan , ukuran=:ukuran , stok=:stok WHERE id_tas=:id_tas")
    void update(int id_tas, String nama, String warna, String harga, String bahan, String ukuran, String stok);

    @Query("SELECT * FROM tas WHERE id_tas=:id_tas")
    Tas get(int id_tas);

    @Query("DELETE FROM tas WHERE id_tas=:id")
    void delete(int id);

}
