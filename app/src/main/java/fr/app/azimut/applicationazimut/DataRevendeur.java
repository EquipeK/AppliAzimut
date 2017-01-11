package fr.app.azimut.applicationazimut;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import fr.app.azimut.applicationazimut.activities.MapsActivity;
import fr.app.azimut.applicationazimut.utils.HttpHandler;

/**
 * Created by Gwendal on 10/01/2017.
 */

public class DataRevendeur extends AsyncTask<Void, Void, Void> {


    private ProgressDialog progressDialog;
    private static String Jsonurl = "http://192.168.30.210/webservice/public/products";
    public String TAG = MapsActivity.class.getSimpleName();
    ArrayList<HashMap<String, String>> productJsonList;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        //progressDialog = new ProgressDialog(DataActivity.this);
        //progressDialog.setMessage("Please wait...");
        //progressDialog.setCancelable(false);
        //progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler httpHandler = new HttpHandler();

        // request to json data url and getting response
        String jsonString = httpHandler.makeServiceCall(Jsonurl);
        Log.e(TAG, "Response from url: " + jsonString);
        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                // Getting JSON Array node
                JSONArray products = jsonObject.getJSONArray("products");

                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);
                    String id = c.getString("id");
                    String ref = c.getString("ref");
                    String name = c.getString("name");
                    String path_img = c.getString("path_img");
                    String description = c.getString("description");


                    // tmp hash map for single product
                    HashMap<String, String> product = new HashMap<>();

                    // adding each child node to HashMap key => value
                    product.put("id", id);
                    product.put("ref", ref);
                    product.put("name", name);
                    product.put("path_img", path_img);
                    product.put("description", description);

                    // adding contact to contact list
                    productJsonList.add(product);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });*/

            }
        } else {
            Log.e(TAG, "Could not get json from server.");
             /* runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Could not get json from server.",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });*/

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
        /*ListAdapter adapter = new SimpleAdapter(
                DataActivity.this, productJsonList,
                R.layout.list_item, new String[]{"name", "description",
                "path_img"}, new int[]{R.id.name,
                R.id.description, R.id.path_img});

        listView.setAdapter(adapter);*/
    }

}
