package com.juara.daftarfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
Button btnSearch;
EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.rc);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setVisibility(View.INVISIBLE);
        txtSearch = findViewById(R.id.txtSearch);
   /*     txtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Toast.makeText(MainActivity.this,txtSearch.getText().toString(),Toast.LENGTH_SHORT).show();
             //
                //   callDaftarFilm(txtSearch.getText().toString());
                return false;
            }
        });*/

   txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
       @Override
       public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

          if (actionId == 5){
              callDaftarFilm(txtSearch.getText().toString());
          }
           return false;
       }
   });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDaftarFilm(txtSearch.getText().toString());
            }
        });


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
                if (dataFilm != null && dataFilm.getResponse().equalsIgnoreCase("true")) {

                    loadData(dataFilm.getSearch());

                } else {

                    Toast.makeText(MainActivity.this, "Film tidak ditemukan", Toast.LENGTH_LONG).show();
                  /*  try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }*/
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