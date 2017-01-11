package fr.app.azimut.applicationazimut.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import fr.app.azimut.applicationazimut.adapters.AdapterShoppingCart;
import fr.app.azimut.applicationazimut.Product;
import fr.app.azimut.applicationazimut.R;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewShoppingCart;
    private RecyclerView.Adapter adapterShoppingCart;
    private RecyclerView.LayoutManager layoutManagerShoppingManager;
    private Button btnSendCommand;
    private String[] dataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        Intent i = new Intent();
        Bundle b = i.getExtras();

        ArrayList<Product> arrayListp =  getIntent().getParcelableArrayListExtra("obj");

        recyclerViewShoppingCart = (RecyclerView) findViewById(R.id.recyclerShoppingCart);
        btnSendCommand = (Button) findViewById(R.id.btnSendCommand);

        recyclerViewShoppingCart.setLayoutManager(new LinearLayoutManager(this));

        adapterShoppingCart = new AdapterShoppingCart(getApplicationContext(),arrayListp);
        recyclerViewShoppingCart.setAdapter(adapterShoppingCart);

        btnSendCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
