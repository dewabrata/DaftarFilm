package com.juara.daftarfilm.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.juara.daftarfilm.DetailFilm;
import com.juara.daftarfilm.MainActivity;
import com.juara.daftarfilm.Model.DaftarFilm.Search;
import com.juara.daftarfilm.R;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

private List<Search> filmList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView judul, deskripsi, tanggal;
    public ImageView image;
    public LinearLayout ll;

    public MyViewHolder(View view) {
        super(view);
        judul = (TextView) view.findViewById(R.id.txtNama);
        deskripsi = (TextView) view.findViewById(R.id.txtNik);
        tanggal = (TextView) view.findViewById(R.id.txtDate);
        image = (ImageView)view.findViewById(R.id.imageView);
        ll = (LinearLayout)view.findViewById(R.id.linearmain);
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

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id  = film.getImdbID();
            //    Toast.makeText(v.getContext(),id,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(), DetailFilm.class);
                intent.putExtra("id",id);
                v.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {

        return filmList.size();
    }
}