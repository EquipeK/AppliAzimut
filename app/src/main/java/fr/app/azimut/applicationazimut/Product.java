package fr.app.azimut.applicationazimut;

import android.os.Parcel;
import android.os.Parcelable;

import fr.app.azimut.applicationazimut.interfaces.IProduct;

/**
 * Created by Gwendal on 10/01/2017.
 */
public class Product implements IProduct, Parcelable{
    private int id; int thumb;
    private String reference, name, describe;
    public boolean isSelected;
    public Product(){

    }

    public Product(int id_,int thumb_,String reference_, String name_, String describe_, boolean isSelected_){
        id = id_; thumb = thumb_;reference = reference_; name = name_; describe = describe_;  isSelected = isSelected_;
    }

    public Product(Parcel in){
        id = in.readInt();
        thumb = in.readInt();
        reference = in.readString();
        name = in.readString();
        describe = in.readString();
        isSelected = Boolean.parseBoolean(in.readString());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId(){
        return id;
    }

    public void setId(int id_){ id = id_;}

    public String getReference(){
        return reference;
    }

    public void setReference(String reference_){
        reference = reference_;
    }

    public String getName(){
        return name;
    }

    public void setName(String name_){
        name = name_;
    }

    public String getDescribe(){
        return describe;
    }

    public void setDescribe(String describe_){
        describe = describe_;
    }

    public int getThumb(){
        return thumb;
    }

    public void setThumb(int thumb_){
        thumb = thumb_;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(thumb);
        dest.writeString(reference);
        dest.writeString(describe);
        dest.writeString(name);
        dest.writeString(isSelected+"");
    }

    public static final Parcelable.Creator<Product> PRODUCT = new Parcelable.Creator<Product>(){

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}

