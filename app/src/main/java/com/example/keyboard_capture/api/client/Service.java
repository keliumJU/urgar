package com.example.keyboard_capture.api.client;

import com.example.keyboard_capture.api.Constants;
import com.example.keyboard_capture.model.Captura;
import com.example.keyboard_capture.model.Hijo;
import com.example.keyboard_capture.model.Padre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Created by Juan on 31/10/2017.
 */

public interface Service {

    //CALL resources of API
    //Cambiar parametros por @File...
    @Headers( "Content-Type: application/json")
    @POST(Constants.PADRE)
    Call<Padre> createPadre(@Body Padre padre);

    @Headers( "Content-Type: application/json")
    @POST(Constants.HIJO)
    Call<Hijo> newChildren(@Body Hijo Hijo);

    @Headers( "Content-Type: application/json")
    @POST(Constants.CAPTURA)
    Call<Captura> newCapture(@Body Captura caputura);

    /*Data Examples
    @GET(Constants.POSTS)
    Call<List<Posts>> getDataPosts();
    @GET(Constants.COMMENTS)
    Call<List<Comments>> getComments();
    @GET(Constants.USERS)
    Call<List<Users>> getUsers();
    @GET(Constants.POSTS+"/{idPost}")
    Call<SinglePost> getPostSingle(@Path("idPost") Integer idPost);
    @GET(Constants.COMMENTS+"/")
    Call<List<CommentsPosts>> getPosts(@Query("postId") Integer postId);
    //@POST(Constants.CALL_POST)
    //Call<List<UserApi>> postGetDataUser(@Body UserApi userApi);
    @FormUrlEncoded
    @POST(Constants.CALL_POST)
    Call<List<UserTest>> postGetDataUser(@Field("ssId") String ssid);
    @GET(Constants.BASE)
    Call<List<UserTest>> getUserTest(@Query("user") String user, @Query("password") String password);
    @GET(Constants.CALL_POST)
    Call<List<UserTest>> newPruebaPostUrl(@Query("ssId") String ssid);
    @GET(Constants.BASE)
    Call<List<UserTest>> baseRequest();
     */

}

