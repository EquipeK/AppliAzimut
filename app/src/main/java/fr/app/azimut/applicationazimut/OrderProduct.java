package fr.app.azimut.applicationazimut;

/**
 * Created by Gwendal on 11/01/2017.
 */

public class OrderProduct {
    private int id_order_product, id_order, id_product, quantite;

    public OrderProduct(int id_order_product_, int id_order_, int id_product_, int quantite_){
        id_order = id_order_;
        id_order_product = id_order_product_;
        id_product = id_product_;
        quantite = quantite_;
    }

}
