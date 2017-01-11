package fr.app.azimut.applicationazimut;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Gwendal on 10/01/2017.
 */

public class ProductParcelable extends ArrayList<Product> implements Parcelable {
    public ProductParcelable(){

    }

    public ProductParcelable(Parcel in) {
        this.getFromParcel(in);
    }


    public static final Creator<ProductParcelable> CREATOR = new Creator<ProductParcelable>() {
        @Override
        public ProductParcelable createFromParcel(Parcel in) {
            return new ProductParcelable(in);
        }

        @Override
        public ProductParcelable[] newArray(int size) {
            return new ProductParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int size = this.size();
        dest.writeInt(size);
        for(int i = 0; i<size; i++){
            Product p = this.get(i);
            dest.writeInt(p.getId());
            dest.writeInt(p.getThumb());
            dest.writeString(p.getName());
            dest.writeString(p.getReference());
            dest.writeString(p.getDescribe());
            dest.writeString(""+p.isSelected);
        }
    }
    private void getFromParcel(Parcel in) {
        this.clear();
        int size = in.readInt();

        for(int i = 0; i<size; i++){
            Product p = new Product();
            p.setId(in.readInt());
            p.setThumb(in.readInt());
            p.setName(in.readString());
            p.setReference(in.readString());
            p.setDescribe(in.readString());
            p.isSelected = Boolean.parseBoolean(in.readString());
        }
    }
}
