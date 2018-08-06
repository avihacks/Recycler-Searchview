package com.testdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.testdemo.Model.Products;
import com.testdemo.R;

import java.util.ArrayList;

import static com.testdemo.Activities.CartActivity.txt_total;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    private ArrayList<Products> productList;
    private ArrayList<Products> updatedatalist;


    public CartAdapter(Context context, ArrayList<Products> productList) {
        this.context = context;
        this.productList = productList;
        this.updatedatalist = productList;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_items, parent, false);


        return new CartAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.MyViewHolder holder, final int i) {

        final Products products = productList.get(i);

        holder.cart__title.setText(productList.get(i).getProduct_name());
        holder.cart__quan.setText(""+productList.get(i).getQuantity());
        holder.cart__price.setText(""+productList.get(i).getPrice());
        Glide.with(context).load(productList.get(i).getImage_url())
                .apply(RequestOptions.centerCropTransform()).into(holder.cart_image);


        holder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "Added : " + products.getPrice(), Toast.LENGTH_SHORT).show();


                int prize = productList.get(i).getPrice();
                int a = Integer.parseInt(holder.cart__quan.getText().toString());
                a++;
                holder.cart__quan.setText(Integer.toString(a));

                int total = addtotal(a,prize);



               if(total >=0){
                  txt_total.setText(""+total);
               }
            }
        });

        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "Removed : " + products.getProduct_name(), Toast.LENGTH_SHORT).show();

                int prize = productList.get(i).getPrice();
                int a = Integer.parseInt(holder.cart__quan.getText().toString());
                a--;
                if(a >=0) {
                    holder.cart__quan.setText(Integer.toString(a));
                }
                int total = addtotal(a,prize);



                if(total >=0){
                    txt_total.setText(""+total);
                }
            }
        });
     //   int quantity = products.getQuantity();

      //  holder.txt_total.setText(""+grandTotal(productList));
        }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
       /* if(productList != null){
            return productList.size();
        }*/
        return productList.size();
    }

    /**
     * Viewholder
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView cart__title, cart__quan, cart__price,txtitemtotal;
        public ImageView cart_image,img_add,img_remove;
        public MyViewHolder(View itemView) {
            super(itemView);
            cart__title = (TextView) itemView.findViewById(R.id.cart_name);
            cart__quan = (TextView) itemView.findViewById(R.id.cart__quan);
            cart__price = (TextView) itemView.findViewById(R.id.cart__prize);
            cart_image = itemView.findViewById(R.id.cart_image);
            img_add = itemView.findViewById(R.id.cart_img_add);
            img_remove = itemView.findViewById(R.id.cart_img_remove);

            txtitemtotal = itemView.findViewById(R.id.txt_total);

        }
    }



    public int grandTotal(ArrayList<Products> items){

        int totalPrice = 0;
        for(int i = 0 ; i < items.size(); i++) {
            totalPrice += items.get(i).getPrice();
        }
        return totalPrice;
    }


    public int grandTotaladd(ArrayList<Products> items,int quant){

        int totalPrice = 0;
        for(int i = 0 ; i < items.size(); i++) {
            totalPrice = items.get(i).getPrice()*quant;
        }
        return totalPrice;
    }

  public int addtotal(int num,int prize){
        int total = num * prize;
        return total;
  }

}
