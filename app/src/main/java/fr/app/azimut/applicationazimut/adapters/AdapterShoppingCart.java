package fr.app.azimut.applicationazimut.adapters;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.app.azimut.applicationazimut.Product;
import fr.app.azimut.applicationazimut.R;
import fr.app.azimut.applicationazimut.ViewHolder.ViewHolderShoppingCart;

/**
 * Created by Gwendal on 11/01/2017.
 */

public class AdapterShoppingCart extends RecyclerView.Adapter<ViewHolderShoppingCart> {

    private Context context;
    private ArrayList<Product> productsToCommand;
    private EditText quantite;
    private String[] dataset;
    public AdapterShoppingCart(Context context_, ArrayList<Product> productsToCommand_){
        context = context_;
        productsToCommand = productsToCommand_;
    }

    @Override
    public ViewHolderShoppingCart onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vue_cell = inflater.inflate(R.layout.list_cell_shopping_cart,parent,false);
        return new ViewHolderShoppingCart(vue_cell,context);
    }

    @Override
    public void onBindViewHolder(ViewHolderShoppingCart holder, int position) {
        final Product product = productsToCommand.get(position);
        holder.display(product);

        quantite = (EditText) holder.itemView.findViewById(R.id.editTextQuantite);

        List<Pair<Product,String>> productCommand = Arrays.asList(
                Pair.create(product,"")
        );
    }

    @Override
    public int getItemCount() {
        return productsToCommand.size();
    }
}

