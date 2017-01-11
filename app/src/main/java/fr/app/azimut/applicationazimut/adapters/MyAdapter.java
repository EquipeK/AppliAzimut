package fr.app.azimut.applicationazimut.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import fr.app.azimut.applicationazimut.ViewHolder.MyViewHolder;
import fr.app.azimut.applicationazimut.Product;
import fr.app.azimut.applicationazimut.R;
import fr.app.azimut.applicationazimut.interfaces.IProduct;

/**
 * Created by Gwendal on 11/01/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private Button btn_command;
    private CheckBox checkBox;
    private ArrayList<IProduct> products;
    public MyAdapter(Context context_, ArrayList<IProduct> products_){
        products = products_;
        context = context_;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vue_cell = inflater.inflate(R.layout.list_cell,parent,false);
        return new MyViewHolder(vue_cell,context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Product product = (Product) products.get(position);
        holder.display(product);

        checkBox = (CheckBox) holder.itemView.findViewById(R.id.checkbox_cart);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ((Product) products.get(position)).isSelected = true;
                }
                else{
                    ((Product) products.get(position)).isSelected = false;
                }
//                Toast.makeText(context,isChecked+"",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public ArrayList<IProduct> getProductsList(){
        return products;
    }

}

