package com.jainshobhit.coldstar;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRef {
    private static final String url="https://api.themoviedb.org/";
    private static ApiRef clientObject;
    private static Retrofit retrofit;

    ApiRef(){
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized ApiRef getInstance()
    {
        if(clientObject==null) clientObject = new ApiRef();
        return clientObject;
    }

    TMDBapi getData(){
        return retrofit.create(TMDBapi.class);
    }
}
