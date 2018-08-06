package com.testdemo.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.testdemo.Adapter.CartAdapter;
import com.testdemo.Adapter.ProductAdapter;
import com.testdemo.Model.Products;
import com.testdemo.R;

import java.util.ArrayList;

import static com.testdemo.Adapter.ProductAdapter.cartlist;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter mAdapter;
    private ProgressDialog mprogressDialog;

    public static TextView txt_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        init();
        Log.e("list data",""+cartlist);

        txt_total.setText(""+ mAdapter.grandTotal(cartlist));

    }

    private void init() {
        txt_total = findViewById(R.id.txt_total);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_cart);
        mAdapter = new CartAdapter(this,cartlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

    }



}
