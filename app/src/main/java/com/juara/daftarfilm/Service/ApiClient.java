package com.juara.daftarfilm.Service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


        public static String BASE_URL ="http://www.omdbapi.com/";

        private static Retrofit retrofit = null;

        public static Retrofit getClient() {
/*
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("X-Api-Key", APIClient.API_KEY).build();
                return chain.proceed(newRequest);
            }
        };
*/
            // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            // OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


            OkHttpClient client = new OkHttpClient.Builder().build();
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(String.class, new StringConverter());
            gb.serializeNulls();
            // gb.excludeFieldsWithoutExposeAnnotation();
            Gson gson = gb.create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiClient.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .client(client)
                    .build();



            return retrofit;
        }

    }

