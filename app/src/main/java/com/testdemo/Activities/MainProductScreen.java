package com.testdemo.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.testdemo.Adapter.ProductAdapter;
import com.testdemo.Listeners.ApiInterface;
import com.testdemo.Model.Products;
import com.testdemo.Network.ApiClient;
import com.testdemo.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainProductScreen extends AppCompatActivity {

    private ArrayList<Products> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private ProgressDialog mprogressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        DisplayProgressDialog();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call< ArrayList<Products>> call = apiService.getProducts();
        call.enqueue(new Callback< ArrayList<Products>>() {
            @Override
            public void onResponse(Call< ArrayList<Products>>call, Response< ArrayList<Products>> response) {

                if (mprogressDialog != null && mprogressDialog.isShowing()) {
                    mprogressDialog.dismiss();
                }
                ArrayList<Products> products = response.body();
            //    Log.e("Response", "Number of product received: " + products.size());
               productList = response.body();
                Log.d("TAG","Response = "+productList);

                mAdapter = new ProductAdapter(MainProductScreen.this,productList);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call< ArrayList<Products>>call, Throwable t) {
                // Log error here since request failed
                Log.e("Response fails", t.toString());
                if (mprogressDialog != null && mprogressDialog.isShowing()) {
                    mprogressDialog.dismiss();
                }
            }
        });


    }


    private void DisplayProgressDialog() {
        mprogressDialog = new ProgressDialog(this);
        mprogressDialog.setMessage("Loading..");
        mprogressDialog.setCancelable(false);
        //mprogressDialog.isIndeterminate = false;
        mprogressDialog.show();
    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_product);

      //  mAdapter = new ProductAdapter(this,productList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        //  recyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_product_screen, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent in = new Intent(MainProductScreen.this,CartActivity.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
              //  mAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mAdapter.getFilter().filter(newText);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }


}
