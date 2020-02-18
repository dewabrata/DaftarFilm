package com.juara.daftarfilm.Service;

import com.juara.daftarfilm.Model.DaftarFilm.DaftarFilmModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterfacesRest {

    @GET("/")
    Call<DaftarFilmModel> getOrder(@Query("apikey") String api_key,@Query("s") String title);
    /*
     @FormUrlEncoded
    @POST("api/komplain/add")
    Call<UpdateKomplain> addKomplain(@Field("username_komplain") String username, @Field("kode_edc") String kode_edc, @Field("masalah") String masalah, @Field("notes_komplain") String notes);
    @GET("api/edc_problem/all")
    Call<MasalahEdcModel> getEDCProblem();
*//*
    @GET("api/dataorder/all")
    Call<ModelOrder> getOrder(@Query("username") String user);
/*
            @Part MultipartBody.Part img1,
           @Part MultipartBody.Part img2,
           @Part MultipartBody.Part img3,
 *//*
   @Multipart
   @POST("api/dataorder/update")
   Call<Komplain> updateData(
           @Part("pod_date") RequestBody podate,
           @Part("status") RequestBody status,
           @Part("lat") RequestBody lat,
           @Part("lon") RequestBody lon,
           @Part("poddate") RequestBody poddate,
           @Part("recievedate") RequestBody recievedate,
           @Part("id") RequestBody id,
           @Part MultipartBody.Part img1
   );
   @Multipart
   @POST("api/komplain/update")
   Call<UpdateKomplain> sendImage(
           @Part("id") RequestBody id,
           @Part("username_penanganan") RequestBody username_komplain,
           @Part("hasil_penanganan") RequestBody masalah,
           @Part("tanggal_penanganan") RequestBody kode_edc,
           @Part("notes_penanganan") RequestBody notes_komplain,
           @Part("status") RequestBody status,
           @Part MultipartBody.Part img1
   );*/

}



