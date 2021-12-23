package com.example.katalogtascanvas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.katalogtascanvas.database.AppDatabase;
import com.example.katalogtascanvas.database.entitas.Tas;

import java.util.List;

public class TasAdapter extends RecyclerView.Adapter<TasAdapter.ViewAdapter> {
    private List<Tas> list;
    private Context context;
    private Dialog dialog;
    AppDatabase database;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    public TasAdapter(Context context, List<Tas> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int view) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_tas,parent,false);
        return new ViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.tvNamaBarang.setText(list.get(position).nama);
        holder.tvDeskripsi.setText(list.get(position).harga);
        holder.tvStok.setText(list.get(position).stok);

        holder.rvTas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                                Intent detail = new Intent(context, ActivityTampil.class);
                                detail.putExtra("id_tas",list.get(position).id_tas);
//                                Toast.makeText(context, " " + list.get(position).id_tas, Toast.LENGTH_SHORT).show();
                                detail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(detail);
                  }
                });
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{

        TextView tvNamaBarang, tvDeskripsi, tvStok;
        RelativeLayout rvTas;


        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            tvNamaBarang = itemView.findViewById(R.id.tv_namaBarang);
            tvDeskripsi =  itemView.findViewById(R.id.tv_deskripsi);
            tvStok = itemView.findViewById(R.id.tv_stokTersedia);
            rvTas = itemView.findViewById(R.id.rvTas);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (dialog!=null){
//                        dialog.onClick(getLayoutPosition());
//                    }
//
//                }
//            });
        }
    }


}
