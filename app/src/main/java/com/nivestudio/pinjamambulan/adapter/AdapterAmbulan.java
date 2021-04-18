package com.nivestudio.pinjamambulan.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nivestudio.pinjamambulan.R;
import com.nivestudio.pinjamambulan.datamodel.DataModelAmbulan;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterAmbulan extends RecyclerView.Adapter<AdapterAmbulan.AmbulanViewHolder> {
    private ArrayList<DataModelAmbulan> dataList, dataSearch;
    private Context context;

    public AdapterAmbulan(ArrayList<DataModelAmbulan> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        this.dataSearch = new ArrayList<DataModelAmbulan>();
        dataSearch.addAll(dataList);
    }

    @NonNull
    @Override
    public AmbulanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_ambulan, parent, false);
        return new AmbulanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmbulanViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNamamobil());
        holder.txtNohp.setText(dataList.get(position).getNohp());

        Glide.with(context).load(dataList.get(position).getGambar().toString()).centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class AmbulanViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama,txtNohp,txtAlamat;
        private CardView cardView;
        private ImageView imageView;

        @SuppressLint("ResourceAsColor")
        public AmbulanViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.namamobilview);
            txtNama.setTextColor(R.color.black);
            txtNohp = (TextView) itemView.findViewById(R.id.nohpview);
            txtNohp.setTextColor(R.color.black);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataList.clear();
        if (charText.length() == 0) {
            dataList.addAll(dataSearch);
            Log.d("alhamdulillah","masuk sini");
        } else {

            for (DataModelAmbulan wp : dataSearch) {
                Log.d("alhamdulillah",wp.getNamamobil().toString());
                if (wp.getNamamobil().toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
