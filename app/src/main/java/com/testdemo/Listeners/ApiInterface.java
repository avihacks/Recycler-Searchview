package com.testdemo.Listeners;

import com.testdemo.Model.Products;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("bins/d5y1e")
    Call< ArrayList<Products>> getProducts();

}
