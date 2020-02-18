package com.juara.daftarfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.juara.daftarfilm.Model.DaftarFilm.DaftarFilmModel;
import com.juara.daftarfilm.Model.DaftarFilm.Search;
import com.juara.daftarfilm.Service.APIInterfacesRest;
import com.juara.daftarfilm.Service.ApiClient;
import com.juara.daftarfilm.adapter.FilmAdapter;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView rc ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.rc);

        callDaftarFilm("terminator");
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;

    public void callDaftarFilm(String judulFilm) {

        apiInterface = ApiClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<DaftarFilmModel> call3 = apiInterface.getFilm("e6525e01", judulFilm);
        call3.enqueue(new Callback<DaftarFilmModel>() {
            @Override
            public void onResponse(Call<DaftarFilmModel> call, Response<DaftarFilmModel> response) {
                progressDialog.dismiss();
                DaftarFilmModel dataFilm = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataFilm != null) {

                    loadData(dataFilm.getSearch());

                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<DaftarFilmModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }

    public void loadData(List<Search> searchs){

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc.setLayoutManager(mLayoutManager);
        rc.setItemAnimator(new DefaultItemAnimator());
        FilmAdapter fa = new FilmAdapter(searchs);
        rc.setAdapter(fa);
    }
}