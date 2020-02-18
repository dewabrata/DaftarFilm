package com.juara.daftarfilm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.juara.daftarfilm.Model.DaftarFilm.Search;
import com.juara.daftarfilm.R;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

private List<Search> filmList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView judul, deskripsi, tanggal;
    public ImageView image;

    public MyViewHolder(View view) {
        super(view);
        judul = (TextView) view.findViewById(R.id.txtNama);
        deskripsi = (TextView) view.findViewById(R.id.txtNik);
        tanggal = (TextView) view.findViewById(R.id.txtDate);
        image = (ImageView)view.findViewById(R.id.imageView);
    }
}


    public FilmAdapter(List<Search> filmList) {
        this.filmList = filmList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Search film = filmList.get(position);
        holder.judul.setText(film.getTitle());
        holder.deskripsi.setText((film.getType()));
        holder.tanggal.setText(film.getYear());
        Picasso.get().load(film.getPoster()).into(holder.image);

    }



    @Override
    public int getItemCount() {
        return filmList.size();
    }
}