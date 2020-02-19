package com.juara.daftarfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.juara.daftarfilm.Model.DaftarFilm.DaftarFilmModel;
import com.juara.daftarfilm.Model.DaftarFilm.Search;
import com.juara.daftarfilm.Model.DetailFilm.Rating;
import com.juara.daftarfilm.Service.APIInterfacesRest;
import com.juara.daftarfilm.Service.ApiClient;
import com.juara.daftarfilm.adapter.FilmAdapter;
import com.juara.daftarfilm.adapter.FilmDetailAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFilm extends AppCompatActivity {

    ImageView imgGambar;
    TextView txtJudul,txtTahun,txtPlot;
    TextView txtAwards,txtLanguage;

    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        imgGambar = findViewById(R.id.gambar);
        txtJudul = findViewById(R.id.txtJudul);
        txtAwards = findViewById(R.id.txtAwards);
        txtTahun = findViewById(R.id.txtTahun);
        txtPlot = findViewById(R.id.txtPlot);
        txtLanguage = findViewById(R.id.txtLanguage);

        String id = getIntent().getStringExtra("id");
        rc = findViewById(R.id.rc);



        callDaftarFilm(id);
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;

    public void callDaftarFilm(String idFilm) {

        apiInterface = ApiClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(DetailFilm.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<com.juara.daftarfilm.Model.DetailFilm.DetailFilm> call3 = apiInterface.getFilmDetail("e6525e01", idFilm);
        call3.enqueue(new Callback<com.juara.daftarfilm.Model.DetailFilm.DetailFilm>() {
            @Override
            public void onResponse(Call<com.juara.daftarfilm.Model.DetailFilm.DetailFilm> call, Response<com.juara.daftarfilm.Model.DetailFilm.DetailFilm> response) {
                progressDialog.dismiss();
                com.juara.daftarfilm.Model.DetailFilm.DetailFilm dataFilm = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataFilm != null && dataFilm.getResponse().equalsIgnoreCase("true")) {
                    Picasso.get().load(dataFilm.getPoster()).into(imgGambar);
                    txtJudul.setText(dataFilm.getTitle());
                    txtTahun.setText(dataFilm.getYear());
                    txtPlot.setText(dataFilm.getPlot());
                    txtLanguage.setText(dataFilm.getLanguage());
                    txtAwards.setText(dataFilm.getAwards());



                    loadData(dataFilm.getRatings());

                } else {

                    Toast.makeText(DetailFilm.this, "Film tidak ditemukan", Toast.LENGTH_LONG).show();
                  /*  try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }*/
                }

            }

            @Override
            public void onFailure(Call<com.juara.daftarfilm.Model.DetailFilm.DetailFilm> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }

    public void loadData(List<Rating> rating){

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc.setLayoutManager(mLayoutManager);
        rc.setItemAnimator(new DefaultItemAnimator());
        FilmDetailAdapter fa = new FilmDetailAdapter(rating);
        rc.setAdapter(fa);

    }
}
