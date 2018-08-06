package com.testdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.testdemo.Model.Products;
import com.testdemo.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> implements Filterable {

    Context context;
    private ArrayList<Products> productList;
    private ArrayList<Products> mFilteredList ;
    public static ArrayList<Products> cartlist = new ArrayList<>();

    public ProductAdapter(Context context, ArrayList<Products> productList) {
        this.context = context;
        this.productList = productList;
       this. mFilteredList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int i) {

        final Products products = mFilteredList.get(i);

        holder.pro_title.setText(mFilteredList.get(i).getProduct_name());
        holder.pro_quan.setText(""+mFilteredList.get(i).getQuantity());
        holder.pro_price.setText(""+mFilteredList.get(i).getPrice());
        Glide.with(context).load(mFilteredList.get(i).getImage_url())
                .apply(RequestOptions.centerCropTransform()).into(holder.image);


        holder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Added : " + products.getProduct_name(), Toast.LENGTH_SHORT).show();
                cartlist.add(products);

              /*  int quantity = products.getQuantity();
                if(quantity > 0)
                products.setQuantity(quantity--);
*/

            }
        });

        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Removed : " + products.getProduct_name(), Toast.LENGTH_SHORT).show();
                cartlist.remove(products);


                    //products.setQuantity(1);
            }
        });


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
        return mFilteredList.size();
    }

    public void setproductList(ArrayList<Products> productList) {
        this.productList = productList;
        this.mFilteredList=productList;
        notifyDataSetChanged();
    }

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     * <p>
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = productList;
                } else {

                    ArrayList<Products> filteredList = new ArrayList<>();
                    for (Products products : productList) {
                        String prize = String.valueOf(products.getPrice());
                        if (products.getProduct_name().toLowerCase().contains(charString.toLowerCase()) ||
                                prize.toLowerCase().contains(charString.toLowerCase())) {

                            filteredList.add(products);

                        }
                    }

                    mFilteredList = filteredList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Products>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    /**
     * Viewholder
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView pro_title, pro_quan, pro_price;
        public ImageView image,img_add,img_remove;
        public MyViewHolder(View itemView) {
            super(itemView);
            pro_title = (TextView) itemView.findViewById(R.id.pro_name);
            pro_quan = (TextView) itemView.findViewById(R.id.pro_quan);
            pro_price = (TextView) itemView.findViewById(R.id.pro_prize);
            image = itemView.findViewById(R.id.image);
            img_add = itemView.findViewById(R.id.img_add);
            img_remove = itemView.findViewById(R.id.img_remove);

        }
    }


}
