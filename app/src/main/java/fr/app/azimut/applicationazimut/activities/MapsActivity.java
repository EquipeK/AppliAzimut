package fr.app.azimut.applicationazimut.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.app.azimut.applicationazimut.utils.HttpHandler;
import fr.app.azimut.applicationazimut.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;

    public String latUsermsg;
    public String longUsermsg;
    public String email;
    public String latVendeur;
    public String longVendeur;
    public String nameVendeur;
    public String idVendeur;

    public String TAG = GeolocActivity.class.getSimpleName();

    public ProgressDialog pDialog;

    // URL to get contacts JSON
    public static String url = "http://192.168.30.210/webservice/public/resellers";

    ArrayList<HashMap<String, String>> resellerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent GeolocIntent = getIntent();
        latUsermsg = GeolocIntent.getStringExtra("latUsermsg");
        longUsermsg = GeolocIntent.getStringExtra("longUsermsg");
        email = GeolocIntent.getStringExtra("email");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        resellerList = new ArrayList<>();
        new GetContacts().execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        double latitude = Double.parseDouble(latUsermsg);
        double longitude = Double.parseDouble(longUsermsg);

        LatLng user = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .position(user));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 8.0f));

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MapsActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            //HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            //String jsonStr = sh.makeServiceCall(url);
            String jsonStr = "C COOL RAOUL";

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj1 = new JSONObject();

                    try {
                        jsonObj1.put("id", "1");
                        jsonObj1.put("name", "Jean Michel Croquette and cie");
                        jsonObj1.put("email", "jeanmimi@gmail.com");
                        jsonObj1.put("lat", "48.3849");
                        jsonObj1.put("lng", "-4.50096");
                        jsonObj1.put("address", "Avenue Vauban");
                        jsonObj1.put("cp", "29200");
                        jsonObj1.put("city", "Brest");
                        jsonObj1.put("created_at", "2016-12-19 12:11:15");
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    JSONObject jsonObj2 = new JSONObject();

                    try {
                        jsonObj2.put("id", "2");
                        jsonObj2.put("name", "Yves");
                        jsonObj2.put("email", "yves@gmail.com");
                        jsonObj2.put("lat", "48.5677");
                        jsonObj2.put("lng", "-3.95028");
                        jsonObj2.put("address", "Avenue Vauban");
                        jsonObj2.put("cp", "29410");
                        jsonObj2.put("city", "Guiclan");
                        jsonObj2.put("created_at", "2016-12-19 12:11:15");
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(jsonObj1);
                    jsonArray.put(jsonObj2);

                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("resellers", jsonArray);


                    // Getting JSON Array node
                    JSONArray resellers = jsonObj.getJSONArray("resellers");

                    // looping through All resellers
                    for (int i = 0; i < resellers.length(); i++) {
                        JSONObject c = resellers.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String lat = c.getString("lat");
                        String lng = c.getString("lng");
                        String address = c.getString("address");
                        String cp = c.getString("cp");
                        String city = c.getString("city");
                        String created_at = c.getString("created_at");

                        // tmp hash map for single contact
                        HashMap<String, String> reseller = new HashMap<>();

                        // adding each child node to HashMap key => value
                        reseller.put("id", id);
                        reseller.put("name", name);
                        reseller.put("email", email);
                        reseller.put("lat", lat);
                        reseller.put("lng", lng);
                        reseller.put("address", address);
                        reseller.put("cp", cp);
                        reseller.put("city", city);
                        reseller.put("created_at", created_at);

                        // adding contact to contact list
                        resellerList.add(reseller);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data
             * */

            for(int i = 0; i < resellerList.size(); i++)
            {

                Iterator it = (Iterator)resellerList.get(i).entrySet().iterator();

                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry)it.next();
                    String keyPair = String.valueOf(pairs.getKey());
                    String valuePair = String.valueOf(pairs.getValue());

                    switch(keyPair) {
                        case "id":
                            idVendeur = valuePair;
                            break;
                        case "name":
                            nameVendeur = valuePair;
                            break;
                        case "lat":
                            latVendeur = valuePair;
                            break;
                        case "lng":
                            longVendeur = valuePair;
                            break;
                        default:
                            break;
                    }

                    it.remove(); // avoids a ConcurrentModificationException
                }

                double latitude = Double.parseDouble(latVendeur);
                double longitude = Double.parseDouble(longVendeur);

                LatLng vendeur = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions()
                        .position(vendeur)
                        .title(nameVendeur)
                        .snippet(idVendeur)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                );
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(final Marker marker) {
                        Intent vendeurIntent = new Intent(MapsActivity.this,MainActivity.class);
                        //String test = marker.getSnippet();
                        vendeurIntent.putExtra("id", marker.getSnippet());
                        startActivity(vendeurIntent);
                    }
                });
            }
        }

    }


}
