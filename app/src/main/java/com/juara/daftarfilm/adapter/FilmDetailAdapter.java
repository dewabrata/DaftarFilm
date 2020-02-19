package com.juara.daftarfilm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.juara.daftarfilm.Model.DaftarFilm.Search;
import com.juara.daftarfilm.Model.DetailFilm.Rating;
import com.juara.daftarfilm.R;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class FilmDetailAdapter extends RecyclerView.Adapter<FilmDetailAdapter.MyViewHolder> {

private List<Rating> filmList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView txtSource;
    public ProgressBar progress;

    public MyViewHolder(View view) {
        super(view);
        txtSource = (TextView) view.findViewById(R.id.txtSource);
        progress = (ProgressBar) view.findViewById(R.id.progressBar);

    }
}


    public FilmDetailAdapter(List<Rating> filmList) {
        this.filmList = filmList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Rating film = filmList.get(position);

        if(film.getSource()!=null) {
            holder.txtSource.setText(film.getSource().toString());
        }
        if(film.getValue().contains("/")){
            List<String> datax =  Arrays.asList((film.getValue().split("/")));
            holder.progress.setProgress((int)(Double.parseDouble(datax.get(0))));
            holder.progress.setMax((Integer.parseInt(datax.get(1))));
        }else{
            String value = film.getValue().substring(0,film.getValue().length()-1);
            holder.progress.setProgress((Integer.parseInt(value)));
            holder.progress.setMax(100);
        }




    }



    @Override
    public int getItemCount() {

        return filmList.size();
    }
}