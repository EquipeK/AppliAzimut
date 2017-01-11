package fr.app.azimut.applicationazimut.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.app.azimut.applicationazimut.Product;
import fr.app.azimut.applicationazimut.R;
import fr.app.azimut.applicationazimut.interfaces.IProduct;

/**
 * Created by Gwendal on 11/01/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView reference; TextView name;
    private CheckBox checkBox;
    private ImageView thumb;
    private Product product;
    private ArrayList<IProduct> products;
    private Context context;
    private RecyclerView recyclerView;
    public MyViewHolder(final View itemView, final Context context_) {
        super(itemView);

        reference = (TextView) itemView.findViewById(R.id.reference);
        name = (TextView) itemView.findViewById(R.id.name);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_cart);
        thumb = (ImageView) itemView.findViewById(R.id.thumb);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler);
        context = context_;
    }

    public void display(Product product_){
        product = product_;
        thumb.setImageResource(product.getThumb());
        reference.setText(product.getReference());
        product.isSelected = checkBox.isSelected();
    }
}
