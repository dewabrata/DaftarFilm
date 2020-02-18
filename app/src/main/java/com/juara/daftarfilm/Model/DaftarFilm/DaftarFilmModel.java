
package com.juara.daftarfilm.Model.DaftarFilm;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaftarFilmModel implements Serializable, Parcelable
{

    @SerializedName("Search")
    @Expose
    private List<Search> search = null;
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String response;
    public final static Creator<DaftarFilmModel> CREATOR = new Creator<DaftarFilmModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DaftarFilmModel createFromParcel(Parcel in) {
            return new DaftarFilmModel(in);
        }

        public DaftarFilmModel[] newArray(int size) {
            return (new DaftarFilmModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 9114293543427694986L;

    protected DaftarFilmModel(Parcel in) {
        in.readList(this.search, (com.juara.daftarfilm.Model.DaftarFilm.Search.class.getClassLoader()));
        this.totalResults = ((String) in.readValue((String.class.getClassLoader())));
        this.response = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public DaftarFilmModel() {
    }

    /**
     * 
     * @param search
     * @param totalResults
     * @param response
     */
    public DaftarFilmModel(List<Search> search, String totalResults, String response) {
        super();
        this.search = search;
        this.totalResults = totalResults;
        this.response = response;
    }

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(search);
        dest.writeValue(totalResults);
        dest.writeValue(response);
    }

    public int describeContents() {
        return  0;
    }

}
